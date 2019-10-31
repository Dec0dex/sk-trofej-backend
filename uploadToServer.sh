#!/bin/bash
set -e
set -o pipefail

# Get information about server
DEPLOY_SERVER='192.168.134.101'
DEPLOY_SERVER_USERNAME='android'
DEPLOY_SERVER_PASSWORD='rootdroid'

# Get information about the project
VERSION=$(mvn -q \
  -Dexec.executable="echo" \
  -Dexec.args='${project.version}' \
  --non-recursive \
  org.codehaus.mojo:exec-maven-plugin:1.6.0:exec)
ARTIFACT=$(mvn -q \
  -Dexec.executable="echo" \
  -Dexec.args='${project.artifactId}' \
  --non-recursive \
  org.codehaus.mojo:exec-maven-plugin:1.6.0:exec)
ARTIFACT_FULL_NAME=${ARTIFACT}-${VERSION}.jar

# Connect to the remote and create dir if doesn't exist
sshpass -p "$DEPLOY_SERVER_PASSWORD" ssh ${DEPLOY_SERVER_USERNAME}@${DEPLOY_SERVER} "mkdir -p /home/${DEPLOY_SERVER_USERNAME}/${ARTIFACT}"

# Copy artifact to the remote
sshpass -p "$DEPLOY_SERVER_PASSWORD" scp target/${ARTIFACT_FULL_NAME} ${DEPLOY_SERVER_USERNAME}@${DEPLOY_SERVER}:/home/${DEPLOY_SERVER_USERNAME}/${ARTIFACT}/${ARTIFACT}.jar

# Connect to the remote and Run spring boot app
sshpass -p "$DEPLOY_SERVER_PASSWORD" ssh -t ${DEPLOY_SERVER_USERNAME}@${DEPLOY_SERVER} """
    sudo ln -s /home/${DEPLOY_SERVER_USERNAME}/${ARTIFACT}/${ARTIFACT}.jar /etc/init.d/${ARTIFACT}

    status=$(systemctl is-active $ARTIFACT)
    activeStatus="active"
    if [ "$status" == "$activeStatus" ]; then
        echo "Restarting spring boot service"
        sudo systemctl daemon-reload
        sudo systemctl restart $ARTIFACT
    else
        echo "Starting spring boot service"
        sudo systemctl enable $ARTIFACT
    fi
"""