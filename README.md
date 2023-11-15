# Introduction 
This automation framework is built with following tool stack
1. Maven
2. Cucumber
3. Java
4. TestNG
5. Extent Reports

Install JDK 11.0.13 
https://www.oracle.com/in/java/technologies/javase/jdk11-archive-downloads.html#license-lightbox

Install Idea Intellij Community Edition 
https://www.jetbrains.com/idea/download/download-thanks.html?platform=windows&code=IIC

Install Maven - 3.6.3
https://maven.apache.org/docs/3.6.3/release-notes.html

Download Windows Application Driver installer from 
https://github.com/microsoft/WinAppDriver/releases/download/v1.2.99/WindowsApplicationDriver-1.2.99-win-x64.exe

Run the installer on a Windows 10 machine where your application under test is installed and will be tested

Enable Developer Mode in Windows settings
https://docs.microsoft.com/en-us/windows/apps/get-started/enable-your-device-for-development

Then you can run the tests from DemoWinAppTest.feature


# Setup Intellij
1.	Download and install latest Intellij Community edition
2.	Download and install "Cucumber for Java" plugin in Intellij
3.	Setup correct source, compile and target version of java i.e. 8 everywhere if using JDK 1.8

# Build and Test
To run tests with tag @ui
mvn test -Dcucumber.filter.tags=@ui

To run tests with tag @ui & @smoke
mvn test -Dcucumber.filter.tags="@ui or @smoke"




# Contribute
TODO: Explain how other users and developers can contribute to make your code better. 

If you want to learn more about creating good readme files then refer the following [guidelines](https://docs.microsoft.com/en-us/azure/devops/repos/git/create-a-readme?view=azure-devops). You can also seek inspiration from the below readme files:
- [ASP.NET Core](https://github.com/aspnet/Home)
- [Visual Studio Code](https://github.com/Microsoft/vscode)
- [Chakra Core](https://github.com/Microsoft/ChakraCore)