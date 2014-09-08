ComS309Project
==========

Things you will need to work with this project:

-Eclipse
-Maven plug-in for Eclipse
-Jetty plug-in for Maven/Eclipse OR Run Jetty Run plug-in for Eclipse (I would recommend this)

*The Jetty plug-in is for temporary testing so you can get use to working on a web app using Maven/Eclipse.
Jetty hosts a local server that you can test with (In our case: http://localhost:8080/ComS309Project/rest/~~~~~~~~~~~).
Once we get our dedicated web server we will be switching over to that.*

Installing our project:

Once you have the Maven plugin for Eclipse you will do the following:

File -> Import -> Maven Project -> ~Your repo folder~

I believe I have it set up so that nothing will need to be changed (until you want it to).

Now you should right click on the project and:

Run As -> Maven Clean

then

Run As -> Maven Install

then assuming you have the run-jetty-run plug-in:

Run -> Debug As -> Run Jetty

You can then go to your web browser and enter the following URL:

http://localhost:8080/ComS309Project/

to see our index page OR you can go to the following URL:

http://localhost:8080/ComS309Project/rest/BasicUserResource

which will perform a very basic REST call that retrieves all usernames from our "database" (which is just fake data in a class until we get our real database).

Using those usernames you can then get some more info on each user by adding that username to our URL as follows:

http://localhost:8080/ComS309Project/rest/BasicUserResource/Alex

this will show you what I typically sell.



**I have run into some small issues with Maven Install where proper JRE/JDK is not setup. If you have this problem let me know (Alex)**
