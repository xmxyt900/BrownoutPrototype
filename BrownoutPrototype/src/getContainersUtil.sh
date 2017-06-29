

#!/bin/sh
ansible DockerCluster1 -m shell -a 'sudo docker stats --no-stream'
#ansible DockerCluster2 -m shell -a 'sudo docker stats --no-stream'
