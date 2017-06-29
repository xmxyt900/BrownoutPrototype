

#!/bin/sh
ansible DockerCluster1 -m shell -a 'sh getCpuUsage.sh'
#ansible DockerCluster2 -m shell -a 'sh getCpuUsage.sh'
