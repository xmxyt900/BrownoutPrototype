

#!/bin/sh
ansible DockerCluster1 -m shell -a 'sh stopRecommendationEngine.sh'
#ansible DockerCluster2 -m shell -a 'sh stopRecommendationEngine.sh'
