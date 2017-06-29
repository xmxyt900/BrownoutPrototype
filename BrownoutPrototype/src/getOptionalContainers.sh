

#!/bin/sh
ansible DockerCluster1 -m shell -a 'sudo docker ps -a --filter "label=brownout.feature=optional"'
#ansible DockerCluster2 -m shell -a 'sudo docker ps -a --filter "label=brownout.feature=optional"'
