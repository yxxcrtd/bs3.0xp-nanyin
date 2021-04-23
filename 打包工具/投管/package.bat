@echo on
@set currentpath=%cd%
cd ../../
@set basepath=%cd%
cd %~dp0
@echo  "BasePath   : %basepath%"
@echo  "BatFilePath: %currentpath%"

@echo  "==============Repalce pom.xml==============="
copy %currentpath%\投管子系统一体化-service.xml %basepath%\microservice\tams-service-container\pom.xml

@echo  "==============Mavne Install==============="
call mvn clean install -f %currentpath%\投管子系统一体化.xml -Dmaven.test.skip=true --settings %1

@echo  "==============Copy Jar==============="
copy %basepath%\tams-system\touguan\app\target\tams-touguan.jar %currentpath%\jar\tams-touguan.jar
copy %basepath%\tams-system\touguan\batch\target\tams-touguan-batch.jar %currentpath%\jar\tams-touguan-batch.jar

@echo  "==============Restore pom.xml==============="
copy %currentpath%\src_pom\service-container-pom.xml %basepath%\microservice\tams-service-container\pom.xml
