set tomcatPath="C:\MyWebServer\apache-tomcat-10.0.20\webapps"

dir src /a-d /b /s *.java > sources.txt
javac -cp .;lib/* -d bin @sources.txt
del sources.txt

cd bin
jar -cvf ../web/WEB-INF/lib/V.jar *

xcopy "*" "../web/WEB-INF/classes" /E /Y /I

cd ../web
jar -cvf %tomcatPath%/V.war *
