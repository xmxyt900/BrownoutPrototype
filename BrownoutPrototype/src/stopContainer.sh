

#!/bin/sh
ansible $1 -m  shell -a 'sudo docker stop '$2''
