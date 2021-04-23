@echo on
@set currentpath=%cd%
cd ../../
@set basepath=%cd%
cd %~dp0
@echo  "BasePath   : %basepath%"
@echo  "BatFilePath: %currentpath%"

@echo  "==============Repalce pom.xml==============="
copy %currentpath%\运营-service.xml %basepath%\microservice\tams-service-container\pom.xml

@echo  "==============Mavne Install==============="
call mvn clean install -f %currentpath%\运营一体化.xml -Dmaven.test.skip=true --settings %1

@echo  "==============Copy Jar==============="
copy %basepath%\tams-system\yunying\app\target\tams-yunying.jar %currentpath%\jar\tams-yunying.jar
copy %basepath%\tams-system\yunying\batch\target\tams-yunying-batch.jar %currentpath%\jar\tams-yunying-batch.jar

@echo  "==============Restore pom.xml==============="
copy %currentpath%\src_pom\service-container-pom.xml %basepath%\microservice\tams-service-container\pom.xml
