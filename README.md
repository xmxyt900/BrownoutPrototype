# BrownoutPrototype
Brownout prototype based on Docker Swarm


Enviroment: Linux System

Requirement: Java SDK, Docker CE

Our testbed is Grid'5000, which is a grid infrastructure located at France, which you need authorized account to access the resource.

How to run the project?
1. cd BrownoutPrototype/src/
2. javac brownout/javac brownout/BrownoutMain.java
3. java brownout/BrownoutMain
>

How to install docker on CentOS?
Ref ：https://store.docker.com/editions/community/docker-ce-server-centos?tab=description
1. Set up the repository
Set up the Docker CE repository on CentOS:
$ sudo yum install -y yum-utils

$ sudo yum-config-manager \
    --add-repo \
    https://download.docker.com/linux/centos/docker-ce.repo

$ sudo yum makecache fast
2. Get Docker CE
Install the latest version of Docker CE on CentOS:
$ sudo yum -y install docker-ce
Start Docker:
$ sudo systemctl start docker
3. Test your Docker CE installation
Test your installation:
$ sudo docker run hello-world





How to install docker on Debian system of Grid'5000?
$ apt-get install -y \
     apt-transport-https \
     ca-certificates \
     curl \
     gnupg2 \
     software-properties-common
Add Docker’s Official GPG key:
$ curl -fsSL https://download.docker.com/linux/debian/gpg | apt-key add -
Set up the stable repository:
$ add-apt-repository \
   "deb [arch=amd64] https://download.docker.com/linux/debian \
   $(lsb_release -cs) \
   stable"
$ apt-get update
$ apt-get install -y docker-ce
$ sudo docker run hello-world





How to create a docker swarm?
Ref: https://docs.docker.com/swarm/install-w-machine/#create-a-docker-swarm
$ docker swarm init
A token will be generated. With this token, other worker node can join swarm.

How to start docker service on boot?
$ sudo systemctl enable docker.service





How to Build and push image to docker repository?
Ref: See: https://docs.docker.com/engine/reference/commandline/build/o
$ docker build –t tagName .
$ sudo docker login --username username --password password
$ sudo docker tag my-image username/my-repo
$ sudo docker push username/my-repo





How to limit containers resource?
Ref: https://docs.docker.com/engine/admin/resource_constraints/#configure-the-realtime-scheduler





How to install ansible?
Ref: : https://www.digitalocean.com/community/tutorials/how-to-install-and-configure-ansible-on-centos-7
Install Ansible on CentOS
$ sudo yum install epel-release
$ sudo yum install Ansible
Configure Ansible hosts (ansible_ssh_host, ansible_ssh_private_key_file):
$ sudo vim /etc/ansible/hosts

Install Ansible on Debian
#!/bin/bash
deb http://ppa.launchpad.net/ansible/ansible/ubuntu trusty main
sudo apt-key adv --keyserver keyserver.ubuntu.com --recv-keys 93C4A3FD7BB9C367
sudo apt-get update
sudo apt-get install -y ansible 





How to operate with Grid'5000?
Ref: https://www.grid5000.fr/mediawiki/index.php/Getting_Started
Login:
$ ssh login@acess.grid5000.fr
$ ssh youraccount@access.grid5000.fr
Enter Lyon site(could be other sites)
$ ssh lyon

Reserve Resource:
$ oarsub -I -p "cluster='sagittaire' and wattmeter='YES'" -t deploy -l nodes=2,walltime=3:0:0
$ cat $OAR_FILE_NODES
$ kadeploy3 -f $OAR_NODE_FILE -e jessie-x64-nfs -k

Monitor power consumption of Grid'5000 node from web interface:
https://www.grid5000.fr/mediawiki/index.php/Monitoring


Retrieve energy consumption data by job id:
At frontend point
frontend: curl http://kwapi.lyon.grid5000.fr:12000/power/timeseries/?job_id=1156179
result will be timestamp alongwith power data at each second of this job.






How to open specific ports (port 80)?
$ /sbin/iptables -A INPUT -m state --state NEW -m tcp -p tcp --dport 80 -j ACCEPT






How to setup VPN for Grid'5000
Ref: https://www.grid5000.fr/mediawiki/index.php/VPN





How to turn on/off hosts (at the frontend host)?
	fnancy:	kapower3 --off -m graphene-42.nancy.grid5000.fr
After it has been shut down, check its status, and turn it on again:
	fnancy:	kapower3 --status -m graphene-42.nancy.grid5000.fr
kapower3 --on -m graphene-42.nancy.grid5000.fr
Alternatively, you could have rebooted the node, using:
	fnancy:	kareboot3 --reboot-kind simple -m graphene-42.nancy.grid5000.fr




How to deploy a online shopping system (Sock shop)on Docker Cluter?
$ docker stack deploy -c v3-docker-compose-brownout.yml weaveshop





How to get into a container?
$ docker exec -it <mycontainer> bash
With root permission
$ docker exec -ti --user root <container-id> /bin/bash
Notes if ‘bash’ does not work, try ‘sh’




How to use JMeter?
Ref: http://jmeter.apache.org/download_jmeter.cgi
On Grid'5000
$ ./jmeter.sh -n -t WeaveShopGrid5k.jmx -l result.csv -e -o resultfolder
WeaveShopGrid5k.jmx	is the requests file containing requested pages and request rate
Results (response time etc.) can be found in resultfoler.






Problems may meet:
Awk problem；awk: illegal reference to array a
try:
$ sudo apt-get install gawk

