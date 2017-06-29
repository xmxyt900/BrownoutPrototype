

#!/bin/sh
ansible $1 -m shell -a "sudo docker inspect "$2" | grep 'service.id'"
