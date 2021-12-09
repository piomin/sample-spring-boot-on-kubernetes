custom_build('piomin/sample-spring-boot-on-kubernetes',
  'mvn package jib:dockerBuild -Pjib -Djib.to.image=$EXPECTED_REF',
  deps=['src'])
k8s_yaml(['k8s/mongodb-deployment.yaml', 'k8s/deployment.yaml'])
k8s_resource('sample-spring-boot-on-kubernetes-deployment',
  port_forwards=8000)