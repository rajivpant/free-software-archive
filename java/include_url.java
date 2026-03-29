/**

  include_url.java

  Designed and Implemented by
  @author Rajiv Pant (Betul)  betul@rajiv.org  http://rajiv.org

  @version 1.0 1997/Mar/11

Description:	

Since Server Parsed HTML (.sthml, .stm) or Server Side Includes (SSI)
does not provide a mechanism to "include" a remote url (i.e. on another
server), this object can be used to do just that.

This Java Object will work with any web server that can run
server-side Java (even if via CGI or shtml exec).

This component was created to demonstrate how to use Java to
to create Active Server Objects that can be called from asp pages.
The advantage of using Java via asp is that most of the code
becomes easily portable to other environments. So if you move
from the ActiveX server to another server environment, you won't
have to re-create your core server applications.

Practical applications of this:

* One of its uses is to get a block of HTML from a remote ad server 
that includes a link to the advertiser, the ad image, some
accompanying text and even scripts and applets.
The commonly used method of using an 
<img src="http://ad-server/cgi-bin/getad?name"> can only include
an image, but not the link, text, or some code around it.
Combined with a cluster of ad servers, this makes it possible to
rotate ads of any type -- image, multimedia, html, script, applet,
anything.

* To include the output of a CGI from another server of yours.
For example, you wrote this CGI on your Unix server that you
can't port to your new NT server just yet, so you still run it
off your unix server but not directly, you "include" it in some
page in your NT server which does other things too. It could be
your advertisement rotation cgi or something.

* This object can easily be extended to behave like a proxy server
so that remotely included pages can be parsed and modified before
displaying so that the links to local urls, images and other objects
on the pages are converted to remote ones so that the links work
and the images show up. As it is, this object does not do that.
I may do that in some program I use to illustrate html-parsing using
Java.


Usage:

  To use this particular example you need:

  * Windows NT Server 4.0 running
  Internet Information Server 3.0 with Active Server Pages.

  * Microsoft Java VM (Virtual Machine) for Windows version
  build 1257 or later.

  * javareg.exe which comes with the freely available MS SDK for Java
  or with MS Visual J++.

  * The accompanying include_url.asp page that demonstrates how
  to use this in your own page.


  To install it for use on your server, follow these steps:

  * Compile this include_url.java program to include_url.class
  java bytecode. You can do this using MS Visual J++, Symantec
  Visual Cafe, some other java compiler, or using the jvc from
  the Sun's JDK (Java Development Kit) by running 
  "jvc include_url.java". Then you may want to test it by running
  it as a stand-alone java program. You can do this from your 
  Java Development Environment like J++ or Cafe or simply by
  entering "include_url.class" at the CMD prompt provided your
  .class files are associated with the MS Java VM as they should be.

  * Place the complied java class include_url.class in your
  \winnt\java\trustlib folder. You can use the
  "copy include_url.class \winnt\java\trustlib\" command at
  the CMD prompt to do this or you can drag and drop.

  * Register this object as an Active Server Objcet using the
  javareg.exe command. You can enter:
  "javareg /register /class:include_url /progid:include_url"

  * Stop and start IIS. You can use
  net stop w3svc & net stop msftpsvc & net start msftpsvc & net start w3svc
  (If you are running the gopher service, you need to restart that in
  the above command too since it is part of INETINFO.EXE.)

  * Try out this object using the sample test-include_url.asp
  asp page at the end of this source code listing.

  Tip: Before compling the final version of any program for
  installation, remember to turn debugging off and set optimizations
  in your development environment such as Visual J++ or Cafe.
*/


import java.net.* ;
import java.io.* ;

class include_url
{
	
	
//		final static boolean DEBUG = true ;
		final static boolean DEBUG = false ;



	String display(String url_to_include)
	{
		String output = "" ;
		
		try 
		{
			
			URL u = new URL (url_to_include) ;
			
			if (DEBUG == true)
			{
				output = output + u.toString() ;
			}

			
			DataInputStream d = new DataInputStream ( u.openStream() ) ;
			
			String l ;
			
			while ( ( l = d.readLine () ) != null )
			{
				output = output + l ;
			}
			
		}
		catch (Exception e)
		{
			output = "Betul's include_url.java reported error: " + e.toString() ;
		}
		
		
		return output ;
		
	} // end of method display()
	

	// The main function is provided so that you can test this
	// object from the command line by specifying a complete
	// URL as the first argument.
	
	// You may want to not have the main function in the final,
	// to-be-installed version.

	public static void main (String[] args)
	{
		
		include_url iu = new include_url() ;
		
		String d = iu.display (args [0]) ;
		
		System.out.println ( d );
	}
	
} // end of class include_url

