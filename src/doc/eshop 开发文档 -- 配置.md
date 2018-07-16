# eshop 开发文档 -- 配置

## 技术选型
    java 
      spring spring-mvc hibernate 
    web  
      jquery bootstrap 
## 使用工具
    ideaIC 2018,maven3
## 搭建
    目录
    
    ├─src
    │  ├─doc                            ##文档说明目录
    │  ├─main                           ##程序代码目录
    │  │  ├─java                        ##java代码目录
    │  │  ├─resources                   ##配置文件目录
    │  │  └─webapp                      ##html,js代码目录
    │  │      └─WEB-INF
    │  └─test                           ##测试代码目录
    │      ├─java                       ##java测试代码目录
    │      └─resources                  ##测试配置文件目录

  







    pom.xml文件与src同级

### pom.xml文件配置

> 主要jar包版本

```pom.xml
  <properties>
    <v.swagger>2.6.0</v.swagger>
    <project.build.jdk>1.8</project.build.jdk>
    <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    <v.hibernate>5.2.2.Final</v.hibernate>
    <v.spring>4.3.5.RELEASE</v.spring>
    <v.jackson>2.8.3</v.jackson>
  </properties>
```

> spring 组管理

```pom.xml
  <dependencyManagement>
    <dependencies>
      <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-framework-bom</artifactId>
        <version>${v.spring}</version>
        <type>pom</type>
        <scope>import</scope>
      </dependency>
    </dependencies>
  </dependencyManagement>
```

> 依赖

```pom.xml
<dependencies>

    <!-- https://mvnrepository.com/artifact/javax.servlet/jstl -->
    <dependency>
      <groupId>javax.servlet</groupId>
      <artifactId>jstl</artifactId>
      <version>1.2</version>
    </dependency>

    <dependency>
      <groupId>javax.servlet</groupId>
      <artifactId>javax.servlet-api</artifactId>
      <version>3.1.0</version>
      <scope>compile</scope>
    </dependency>
    <dependency>
      <groupId>junit</groupId>
      <artifactId>junit</artifactId>
      <version>4.12</version>
      <scope>test</scope>
    </dependency>

    <dependency>
      <groupId>log4j</groupId>
      <artifactId>log4j</artifactId>
      <version>1.2.17</version>
    </dependency>
    <dependency>
      <groupId>org.slf4j</groupId>
      <artifactId>slf4j-log4j12</artifactId>
      <version>1.7.25</version>
    </dependency>

    <dependency>
      <groupId>com.liferay</groupId>
      <artifactId>org.apache.commons.fileupload</artifactId>
      <version>1.2.2.LIFERAY-PATCHED-1</version>
    </dependency>

    <!-- spring -->
    <dependency>
      <groupId>com.fasterxml.jackson.core</groupId>
      <artifactId>jackson-annotations</artifactId>
      <version>${v.jackson}</version>
    </dependency>

    <dependency>
      <groupId>com.fasterxml.jackson.core</groupId>
      <artifactId>jackson-databind</artifactId>
      <version>${v.jackson}</version>
    </dependency>

    <dependency>
      <groupId>com.fasterxml.jackson.core</groupId>
      <artifactId>jackson-core</artifactId>
      <version>${v.jackson}</version>
    </dependency>
    <dependency>
      <groupId>org.aspectj</groupId>
      <artifactId>aspectjweaver</artifactId>
      <version>1.8.9</version>
    </dependency>
    <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-jdbc</artifactId>
    </dependency>
    <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-web</artifactId>
    </dependency>
    <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-webmvc</artifactId>
    </dependency>
    <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-orm</artifactId>
    </dependency>
    <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-test</artifactId>
    </dependency>
    <dependency>
      <groupId>com.jayway.jsonpath</groupId>
      <artifactId>json-path</artifactId>
      <version>2.2.0</version>
    </dependency>
    <!-- /spring -->

    <!--数据库 -->
 
    <dependency>
      <groupId>com.mchange</groupId>
      <artifactId>c3p0</artifactId>
      <version>0.9.5.2</version>
    </dependency>
    <dependency>
      <groupId>mysql</groupId>
      <artifactId>mysql-connector-java</artifactId>
      <version>5.1.38</version>
      <!--<version>8.0.11</version>-->
    </dependency>



    <!--/数据库 -->
    
    <dependency>
    <groupId>cglib</groupId>
    <artifactId>cglib</artifactId>
    <version>3.2.4</version>
    </dependency>

    <dependency>
      <groupId>net.sf.ehcache</groupId>
      <artifactId>ehcache</artifactId>
      <version>2.10.1</version>
    </dependency>
    <dependency>
      <groupId>org.hibernate</groupId>
      <artifactId>hibernate-core</artifactId>
      <version>${v.hibernate}</version>
    </dependency>
    <dependency>
      <groupId>org.hibernate</groupId>
      <artifactId>hibernate-ehcache</artifactId>
      <version>${v.hibernate}</version>
    </dependency>
    <dependency>
      <groupId>org.hibernate</groupId>
      <artifactId>hibernate-validator</artifactId>
      <version>${v.hibernate}</version>
    </dependency>
    <!--/hibernate -->

    <dependency>
      <groupId>io.springfox</groupId>
      <artifactId>springfox-swagger2</artifactId>
      <version>${v.swagger}</version>
    </dependency>

    <dependency>
      <groupId>org.json</groupId>
      <artifactId>json</artifactId>
      <version>20160810</version>
    </dependency>

    <dependency>
      <groupId>com.belerweb</groupId>
      <artifactId>pinyin4j</artifactId>
      <version>2.5.1</version>
    </dependency>
    <dependency>
      <groupId>net.sf.json-lib</groupId>
      <artifactId>json-lib</artifactId>
      <version>2.4</version>
    </dependency>

  </dependencies>
```

> 编译与插件

```pom.xml
<build>
    <finalName>eshop</finalName>
    <plugins>
      <plugin>
        <artifactId>maven-war-plugin</artifactId>
        <version>2.3</version>
        <configuration>
          <failOnMissingWebXml>false</failOnMissingWebXml>
        </configuration>
      </plugin>
      <plugin>
        <groupId>org.apache.maven.plugins</groupId>
        <artifactId>maven-compiler-plugin</artifactId>
        <version>3.1</version>
        <configuration>
          <source>1.8</source>
          <target>1.8</target>
          <encoding>UTF-8</encoding>
        </configuration>
      </plugin>

      <plugin>
        <groupId>org.eclipse.jetty</groupId>
        <artifactId>jetty-maven-plugin</artifactId>
        <version>9.3.12.v20160915</version>
        <configuration>
          <scanIntervalSeconds>10</scanIntervalSeconds>
          <webApp>
            <contextPath>/eshop</contextPath>
            <defaultsDescriptor>src/main/resources/webdefault.xml</defaultsDescriptor>
            <descriptor>${project.basedir}/src/main/webapp/WEB-INF/web.xml</descriptor>
          </webApp>
          <httpConnector>
            <port>80</port>
          </httpConnector>
        </configuration>
      </plugin>


    </plugins>
  </build>
```



#### jetty 配置  

> 在main/resources下建文件webdefault.xml，内容如下

```webdefault.xml
<?xml version="1.0" encoding="UTF-8"?>
<!-- ===================================================================== -->
<!-- This file contains the default descriptor for web applications.       -->
<!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -->
<!-- The intent of this descriptor is to include jetty specific or common  -->
<!-- configuration for all webapps.   If a context has a webdefault.xml    -->
<!-- descriptor, it is applied before the contexts own web.xml file        -->
<!--                                                                       -->
<!-- A context may be assigned a default descriptor by:                    -->
<!--  + Calling WebApplicationContext.setDefaultsDescriptor                -->
<!--  + Passed an arg to addWebApplications                                -->
<!--                                                                       -->
<!-- This file is used both as the resource within the jetty.jar (which is -->
<!-- used as the default if no explicit defaults descriptor is set) and it -->
<!-- is copied to the etc directory of the Jetty distro and explicitly     -->
<!-- by the jetty.xml file.                                                -->
<!--                                                                       -->
<!-- ===================================================================== -->
<web-app
        xmlns="http://java.sun.com/xml/ns/javaee"
        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
        xsi:schemaLocation="http://java.sun.com/xml/ns/javaee http://java.sun.com/xml/ns/javaee/web-app_2_5.xsd"
        metadata-complete="true"
        version="2.5"
        >

    <description>
        Default web.xml file.
        This file is applied to a Web application before it's own WEB_INF/web.xml file
    </description>

    <!-- ==================================================================== -->
    <!-- Removes static references to beans from javax.el.BeanELResolver to   -->
    <!-- ensure webapp classloader can be released on undeploy                -->
    <!-- ==================================================================== -->
    <listener>
        <listener-class>org.eclipse.jetty.servlet.listener.ELContextCleaner</listener-class>
    </listener>

    <!-- ==================================================================== -->
    <!-- Removes static cache of Methods from java.beans.Introspector to      -->
    <!-- ensure webapp classloader can be released on undeploy                -->
    <!-- ==================================================================== -->
    <listener>
        <listener-class>org.eclipse.jetty.servlet.listener.IntrospectorCleaner</listener-class>
    </listener>


    <!-- ==================================================================== -->
    <!-- Context params to control Session Cookies                            -->
    <!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -  -->
    <!--
      UNCOMMENT TO ACTIVATE <context-param> <param-name>org.eclipse.jetty.servlet.SessionDomain</param-name> <param-value>127.0.0.1</param-value> </context-param> <context-param>
      <param-name>org.eclipse.jetty.servlet.SessionPath</param-name> <param-value>/</param-value> </context-param> <context-param> <param-name>org.eclipse.jetty.servlet.MaxAge</param-name>
      <param-value>-1</param-value> </context-param>
    -->

    <!-- ==================================================================== -->
    <!-- The default servlet.                                                 -->
    <!-- This servlet, normally mapped to /, provides the handling for static -->
    <!-- content, OPTIONS and TRACE methods for the context.                  -->
    <!-- The following initParameters are supported:                          -->
    <!--
   *  acceptRanges      If true, range requests and responses are
   *                    supported
   *
   *  dirAllowed        If true, directory listings are returned if no
   *                    welcome file is found. Else 403 Forbidden.
   *
   *  welcomeServlets   If true, attempt to dispatch to welcome files
   *                    that are servlets, but only after no matching static
   *                    resources could be found. If false, then a welcome
   *                    file must exist on disk. If "exact", then exact
   *                    servlet matches are supported without an existing file.
   *                    Default is true.
   *
   *                    This must be false if you want directory listings,
   *                    but have index.jsp in your welcome file list.
   *
   *  redirectWelcome   If true, welcome files are redirected rather than
   *                    forwarded to.
   *
   *  gzip              If set to true, then static content will be served as
   *                    gzip content encoded if a matching resource is
   *                    found ending with ".gz"
   *
   *  resourceBase      Set to replace the context resource base
   *
   *  resourceCache     If set, this is a context attribute name, which the servlet
   *                    will use to look for a shared ResourceCache instance.
   *
   *  relativeResourceBase
   *                    Set with a pathname relative to the base of the
   *                    servlet context root. Useful for only serving static content out
   *                    of only specific subdirectories.
   *
   *  aliases           If True, aliases of resources are allowed (eg. symbolic
   *                    links and caps variations). May bypass security constraints.
   *
   *  maxCacheSize      The maximum total size of the cache or 0 for no cache.
   *  maxCachedFileSize The maximum size of a file to cache
   *  maxCachedFiles    The maximum number of files to cache
   *
   *  useFileMappedBuffer
   *                    If set to true, it will use mapped file buffer to serve static content
   *                    when using NIO connector. Setting this value to false means that
   *                    a direct buffer will be used instead of a mapped file buffer.
   *                    By default, this is set to true.
   *
   *  cacheControl      If set, all static content will have this value set as the cache-control
   *                    header.
   -->


    <!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -  -->
    <servlet>
        <servlet-name>default</servlet-name>
        <servlet-class>org.eclipse.jetty.servlet.DefaultServlet</servlet-class>
        <init-param>
            <param-name>aliases</param-name>
            <param-value>false</param-value>
        </init-param>
        <init-param>
            <param-name>acceptRanges</param-name>
            <param-value>true</param-value>
        </init-param>
        <init-param>
            <param-name>dirAllowed</param-name>
            <param-value>true</param-value>
        </init-param>
        <init-param>
            <param-name>welcomeServlets</param-name>
            <param-value>true</param-value>
        </init-param>
        <init-param>
            <param-name>redirectWelcome</param-name>
            <param-value>false</param-value>
        </init-param>
        <init-param>
            <param-name>maxCacheSize</param-name>
            <param-value>256000000</param-value>
        </init-param>
        <init-param>
            <param-name>maxCachedFileSize</param-name>
            <param-value>200000000</param-value>
        </init-param>
        <init-param>
            <param-name>maxCachedFiles</param-name>
            <param-value>2048</param-value>
        </init-param>
        <init-param>
            <param-name>gzip</param-name>
            <param-value>true</param-value>
        </init-param>
        <init-param>
            <param-name>useFileMappedBuffer</param-name>
            <param-value>false</param-value>
        </init-param>
        <!--
        <init-param>
          <param-name>resourceCache</param-name>
          <param-value>resourceCache</param-value>
        </init-param>
        -->
        <!--
        <init-param>
          <param-name>cacheControl</param-name>
          <param-value>max-age=3600,public</param-value>
        </init-param>
        -->
        <load-on-startup>0</load-on-startup>
    </servlet>

    <servlet-mapping>
        <servlet-name>default</servlet-name>
        <url-pattern>/</url-pattern>
    </servlet-mapping>


    <!-- ==================================================================== -->
    <!-- JSP Servlet                                                          -->
    <!-- This is the jasper JSP servlet from the jakarta project              -->
    <!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -  -->
    <!-- The JSP page compiler and execution servlet, which is the mechanism  -->
    <!-- used by Glassfish to support JSP pages.  Traditionally, this servlet -->
    <!-- is mapped to URL patterh "*.jsp".  This servlet supports the         -->
    <!-- following initialization parameters (default values are in square    -->
    <!-- brackets):                                                           -->
    <!--                                                                      -->
    <!--   checkInterval       If development is false and reloading is true, -->
    <!--                       background compiles are enabled. checkInterval -->
    <!--                       is the time in seconds between checks to see   -->
    <!--                       if a JSP page needs to be recompiled. [300]    -->
    <!--                                                                      -->
    <!--   compiler            Which compiler Ant should use to compile JSP   -->
    <!--                       pages.  See the Ant documenation for more      -->
    <!--                       information. [javac]                           -->
    <!--                                                                      -->
    <!--   classdebuginfo      Should the class file be compiled with         -->
    <!--                       debugging information?  [true]                 -->
    <!--                                                                      -->
    <!--   classpath           What class path should I use while compiling   -->
    <!--                       generated servlets?  [Created dynamically      -->
    <!--                       based on the current web application]          -->
    <!--                       Set to ? to make the container explicitly set  -->
    <!--                       this parameter.                                -->
    <!--                                                                      -->
    <!--   development         Is Jasper used in development mode (will check -->
    <!--                       for JSP modification on every access)?  [true] -->
    <!--                                                                      -->
    <!--   enablePooling       Determines whether tag handler pooling is      -->
    <!--                       enabled  [true]                                -->
    <!--                                                                      -->
    <!--   fork                Tell Ant to fork compiles of JSP pages so that -->
    <!--                       a separate JVM is used for JSP page compiles   -->
    <!--                       from the one Tomcat is running in. [true]      -->
    <!--                                                                      -->
    <!--   ieClassId           The class-id value to be sent to Internet      -->
    <!--                       Explorer when using <jsp:plugin> tags.         -->
    <!--                       [clsid:8AD9C840-044E-11D1-B3E9-00805F499D93]   -->
    <!--                                                                      -->
    <!--   javaEncoding        Java file encoding to use for generating java  -->
    <!--                       source files. [UTF-8]                          -->
    <!--                                                                      -->
    <!--   keepgenerated       Should we keep the generated Java source code  -->
    <!--                       for each page instead of deleting it? [true]   -->
    <!--                                                                      -->
    <!--   logVerbosityLevel   The level of detailed messages to be produced  -->
    <!--                       by this servlet.  Increasing levels cause the  -->
    <!--                       generation of more messages.  Valid values are -->
    <!--                       FATAL, ERROR, WARNING, INFORMATION, and DEBUG. -->
    <!--                       [WARNING]                                      -->
    <!--                                                                      -->
    <!--   mappedfile          Should we generate static content with one     -->
    <!--                       print statement per input line, to ease        -->
    <!--                       debugging?  [false]                            -->
    <!--                                                                      -->
    <!--                                                                      -->
    <!--   reloading           Should Jasper check for modified JSPs?  [true] -->
    <!--                                                                      -->
    <!--   suppressSmap        Should the generation of SMAP info for JSR45   -->
    <!--                       debugging be suppressed?  [false]              -->
    <!--                                                                      -->
    <!--   dumpSmap            Should the SMAP info for JSR45 debugging be    -->
    <!--                       dumped to a file? [false]                      -->
    <!--                       False if suppressSmap is true                  -->
    <!--                                                                      -->
    <!--   scratchdir          What scratch directory should we use when      -->
    <!--                       compiling JSP pages?  [default work directory  -->
    <!--                       for the current web application]               -->
    <!--                                                                      -->
    <!--   tagpoolMaxSize      The maximum tag handler pool size  [5]         -->
    <!--                                                                      -->
    <!--   xpoweredBy          Determines whether X-Powered-By response       -->
    <!--                       header is added by generated servlet  [false]  -->
    <!--                                                                      -->
    <!-- If you wish to use Jikes to compile JSP pages:                       -->
    <!--   Set the init parameter "compiler" to "jikes".  Define              -->
    <!--   the property "-Dbuild.compiler.emacs=true" when starting Jetty     -->
    <!--   to cause Jikes to emit error messages in a format compatible with  -->
    <!--   Jasper.                                                            -->
    <!--   If you get an error reporting that jikes can't use UTF-8 encoding, -->
    <!--   try setting the init parameter "javaEncoding" to "ISO-8859-1".     -->
    <!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -  -->
    <servlet
            id="jsp"
            >
        <servlet-name>jsp</servlet-name>
        <servlet-class>org.apache.jasper.servlet.JspServlet</servlet-class>
        <init-param>
            <param-name>logVerbosityLevel</param-name>
            <param-value>DEBUG</param-value>
        </init-param>
        <init-param>
            <param-name>fork</param-name>
            <param-value>false</param-value>
        </init-param>
        <init-param>
            <param-name>xpoweredBy</param-name>
            <param-value>false</param-value>
        </init-param>
        <!--
        <init-param>
            <param-name>classpath</param-name>
            <param-value>?</param-value>
        </init-param>
        -->
        <load-on-startup>0</load-on-startup>
    </servlet>

    <servlet-mapping>
        <servlet-name>jsp</servlet-name>
        <url-pattern>*.jsp</url-pattern>
        <url-pattern>*.jspf</url-pattern>
        <url-pattern>*.jspx</url-pattern>
        <url-pattern>*.xsp</url-pattern>
        <url-pattern>*.JSP</url-pattern>
        <url-pattern>*.JSPF</url-pattern>
        <url-pattern>*.JSPX</url-pattern>
        <url-pattern>*.XSP</url-pattern>
    </servlet-mapping>

    <!-- ==================================================================== -->
    <!-- Dynamic Servlet Invoker.                                             -->
    <!-- This servlet invokes anonymous servlets that have not been defined   -->
    <!-- in the web.xml or by other means. The first element of the pathInfo  -->
    <!-- of a request passed to the envoker is treated as a servlet name for  -->
    <!-- an existing servlet, or as a class name of a new servlet.            -->
    <!-- This servlet is normally mapped to /servlet/*                        -->
    <!-- This servlet support the following initParams:                       -->
    <!--                                                                      -->
    <!--  nonContextServlets       If false, the invoker can only load        -->
    <!--                           servlets from the contexts classloader.    -->
    <!--                           This is false by default and setting this  -->
    <!--                           to true may have security implications.    -->
    <!--                                                                      -->
    <!--  verbose                  If true, log dynamic loads                 -->
    <!--                                                                      -->
    <!--  *                        All other parameters are copied to the     -->
    <!--                           each dynamic servlet as init parameters    -->
    <!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -  -->
    <!--
      Uncomment for dynamic invocation <servlet> <servlet-name>invoker</servlet-name> <servlet-class>org.eclipse.jetty.servlet.Invoker</servlet-class> <init-param> <param-name>verbose</param-name>
      <param-value>false</param-value> </init-param> <init-param> <param-name>nonContextServlets</param-name> <param-value>false</param-value> </init-param> <init-param>
      <param-name>dynamicParam</param-name> <param-value>anyValue</param-value> </init-param> <load-on-startup>0</load-on-startup> </servlet> <servlet-mapping> <servlet-name>invoker</servlet-name>
      <url-pattern>/servlet/*</url-pattern> </servlet-mapping>
    -->



    <!-- ==================================================================== -->
    <session-config>
        <session-timeout>30</session-timeout>
    </session-config>

    <!-- ==================================================================== -->
    <!-- Default MIME mappings                                                -->
    <!-- The default MIME mappings are provided by the mime.properties        -->
    <!-- resource in the org.eclipse.jetty.server.jar file.  Additional or modified  -->
    <!-- mappings may be specified here                                       -->
    <!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -  -->
    <!-- UNCOMMENT TO ACTIVATE
    <mime-mapping>
      <extension>mysuffix</extension>
      <mime-type>mymime/type</mime-type>
    </mime-mapping>
    -->

    <!-- ==================================================================== -->
    <welcome-file-list>

        <welcome-file>index.html</welcome-file>
        <welcome-file>index.htm</welcome-file>
        <welcome-file>index.jsp</welcome-file>
    </welcome-file-list>

    <!-- ==================================================================== -->
    <locale-encoding-mapping-list>
        <locale-encoding-mapping>
            <locale>ar</locale>
            <encoding>ISO-8859-6</encoding>
        </locale-encoding-mapping>
        <locale-encoding-mapping>
            <locale>be</locale>
            <encoding>ISO-8859-5</encoding>
        </locale-encoding-mapping>
        <locale-encoding-mapping>
            <locale>bg</locale>
            <encoding>ISO-8859-5</encoding>
        </locale-encoding-mapping>
        <locale-encoding-mapping>
            <locale>ca</locale>
            <encoding>ISO-8859-1</encoding>
        </locale-encoding-mapping>
        <locale-encoding-mapping>
            <locale>cs</locale>
            <encoding>ISO-8859-2</encoding>
        </locale-encoding-mapping>
        <locale-encoding-mapping>
            <locale>da</locale>
            <encoding>ISO-8859-1</encoding>
        </locale-encoding-mapping>
        <locale-encoding-mapping>
            <locale>de</locale>
            <encoding>ISO-8859-1</encoding>
        </locale-encoding-mapping>
        <locale-encoding-mapping>
            <locale>el</locale>
            <encoding>ISO-8859-7</encoding>
        </locale-encoding-mapping>
        <locale-encoding-mapping>
            <locale>en</locale>
            <encoding>ISO-8859-1</encoding>
        </locale-encoding-mapping>
        <locale-encoding-mapping>
            <locale>es</locale>
            <encoding>ISO-8859-1</encoding>
        </locale-encoding-mapping>
        <locale-encoding-mapping>
            <locale>et</locale>
            <encoding>ISO-8859-1</encoding>
        </locale-encoding-mapping>
        <locale-encoding-mapping>
            <locale>fi</locale>
            <encoding>ISO-8859-1</encoding>
        </locale-encoding-mapping>
        <locale-encoding-mapping>
            <locale>fr</locale>
            <encoding>ISO-8859-1</encoding>
        </locale-encoding-mapping>
        <locale-encoding-mapping>
            <locale>hr</locale>
            <encoding>ISO-8859-2</encoding>
        </locale-encoding-mapping>
        <locale-encoding-mapping>
            <locale>hu</locale>
            <encoding>ISO-8859-2</encoding>
        </locale-encoding-mapping>
        <locale-encoding-mapping>
            <locale>is</locale>
            <encoding>ISO-8859-1</encoding>
        </locale-encoding-mapping>
        <locale-encoding-mapping>
            <locale>it</locale>
            <encoding>ISO-8859-1</encoding>
        </locale-encoding-mapping>
        <locale-encoding-mapping>
            <locale>iw</locale>
            <encoding>ISO-8859-8</encoding>
        </locale-encoding-mapping>
        <locale-encoding-mapping>
            <locale>ja</locale>
            <encoding>Shift_JIS</encoding>
        </locale-encoding-mapping>
        <locale-encoding-mapping>
            <locale>ko</locale>
            <encoding>EUC-KR</encoding>
        </locale-encoding-mapping>
        <locale-encoding-mapping>
            <locale>lt</locale>
            <encoding>ISO-8859-2</encoding>
        </locale-encoding-mapping>
        <locale-encoding-mapping>
            <locale>lv</locale>
            <encoding>ISO-8859-2</encoding>
        </locale-encoding-mapping>
        <locale-encoding-mapping>
            <locale>mk</locale>
            <encoding>ISO-8859-5</encoding>
        </locale-encoding-mapping>
        <locale-encoding-mapping>
            <locale>nl</locale>
            <encoding>ISO-8859-1</encoding>
        </locale-encoding-mapping>
        <locale-encoding-mapping>
            <locale>no</locale>
            <encoding>ISO-8859-1</encoding>
        </locale-encoding-mapping>
        <locale-encoding-mapping>
            <locale>pl</locale>
            <encoding>ISO-8859-2</encoding>
        </locale-encoding-mapping>
        <locale-encoding-mapping>
            <locale>pt</locale>
            <encoding>ISO-8859-1</encoding>
        </locale-encoding-mapping>
        <locale-encoding-mapping>
            <locale>ro</locale>
            <encoding>ISO-8859-2</encoding>
        </locale-encoding-mapping>
        <locale-encoding-mapping>
            <locale>ru</locale>
            <encoding>ISO-8859-5</encoding>
        </locale-encoding-mapping>
        <locale-encoding-mapping>
            <locale>sh</locale>
            <encoding>ISO-8859-5</encoding>
        </locale-encoding-mapping>
        <locale-encoding-mapping>
            <locale>sk</locale>
            <encoding>ISO-8859-2</encoding>
        </locale-encoding-mapping>
        <locale-encoding-mapping>
            <locale>sl</locale>
            <encoding>ISO-8859-2</encoding>
        </locale-encoding-mapping>
        <locale-encoding-mapping>
            <locale>sq</locale>
            <encoding>ISO-8859-2</encoding>
        </locale-encoding-mapping>
        <locale-encoding-mapping>
            <locale>sr</locale>
            <encoding>ISO-8859-5</encoding>
        </locale-encoding-mapping>
        <locale-encoding-mapping>
            <locale>sv</locale>
            <encoding>ISO-8859-1</encoding>
        </locale-encoding-mapping>
        <locale-encoding-mapping>
            <locale>tr</locale>
            <encoding>ISO-8859-9</encoding>
        </locale-encoding-mapping>
        <locale-encoding-mapping>
            <locale>uk</locale>
            <encoding>ISO-8859-5</encoding>
        </locale-encoding-mapping>
        <locale-encoding-mapping>
            <locale>zh</locale>
            <encoding>GB2312</encoding>
        </locale-encoding-mapping>
        <locale-encoding-mapping>
            <locale>zh_TW</locale>
            <encoding>Big5</encoding>
        </locale-encoding-mapping>
    </locale-encoding-mapping-list>

    <security-constraint>
        <web-resource-collection>
            <web-resource-name>Disable TRACE</web-resource-name>
            <url-pattern>/</url-pattern>
            <http-method>TRACE</http-method>
        </web-resource-collection>
        <auth-constraint/>
    </security-constraint>

</web-app>
```





### web.xml 文件配置

> <!--程序名 -->

```web.xml
  
  <display-name>E-shop</display-name>
    <context-param>
        <param-name>webAppRootKey</param-name>
        <param-value>LK.Eshop</param-value>
    </context-param>
```

> <!-- 欢迎页 -->

```web.xml
 
 <welcome-file-list>
        <welcome-file>/login/gotoLogin.act</welcome-file>
    </welcome-file-list>
```

> <!-- log4j -->

```
 
    <context-param>
        <param-name>log4jConfigLocation</param-name>
        <param-value>classpath:log4j.properties</param-value>
    </context-param>
    <listener>
        <listener-class>org.springframework.web.util.Log4jConfigListener</listener-class>
    </listener>
```

><!-- spring 配置 -->

```
<context-param>
        <param-name>contextConfigLocation</param-name>
        <param-value>
            classpath:applicationContext*.xml
        </param-value>
    </context-param>
    <listener>
        <listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
    </listener>
```

> <!-- spring-mvc 与api -->

```web.xml
<servlet>
        <servlet-name>eshop</servlet-name>
        <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
        <init-param>
            <param-name>contextConfigLocation</param-name>
            <param-value>classpath:applicationContext-mvc.xml</param-value>
        </init-param>
        <load-on-startup>1</load-on-startup>
    </servlet>
    <servlet-mapping>
        <servlet-name>eshop</servlet-name>
        <url-pattern>*.act</url-pattern>
    </servlet-mapping>
    <servlet-mapping>
        <servlet-name>eshop</servlet-name>
        <url-pattern>/v2/api-docs</url-pattern>
    </servlet-mapping>
```

> <!-- 字符过滤器 -->

```web.xml
   <filter>
        <filter-name>encodingFilter</filter-name>
        <filter-class>org.springframework.web.filter.CharacterEncodingFilter</filter-class>
        <init-param>
            <param-name>encoding</param-name>
            <param-value>UTF-8</param-value>
        </init-param>
    </filter>
    <filter-mapping>
        <filter-name>encodingFilter</filter-name>
        <url-pattern>*.act</url-pattern>
    </filter-mapping>
```



> <!-- hibernate open session -->

```web.xml
    <filter>
        <filter-name>OpenSessionInViewFilter</filter-name>
        <filter-class>org.springframework.orm.hibernate5.support.OpenSessionInViewFilter</filter-class>
    </filter>
    <filter-mapping>
        <filter-name>OpenSessionInViewFilter</filter-name>
        <url-pattern>*.act</url-pattern>
    </filter-mapping>
```

### spring 配置
#### applicationContext.xml

> spring 扫描方式

```
 <context:annotation-config />
    <context:component-scan base-package="com.luke.shop.eshop" use-default-filters="false" >
        <context:include-filter expression="org.springframework.stereotype.Service" type="annotation"/>
        <context:include-filter expression="org.springframework.stereotype.Component" type="annotation"/>
    </context:component-scan>
```

> spring-Swagger-api 配置

```java
 <bean class="com.luke.shop.eshop.api.SwaggerCfg"></bean>
 
 /**SwaggerCfg.java*/
 
@EnableWebMvc
@EnableSwagger2
public class SwaggerCfg {
    @Bean
    public Docket docketCfg(){
        Docket docket = new Docket(DocumentationType.SWAGGER_2) ;
        ApiInfo apiInfo = new ApiInfo(
                "api",
                "api",
                "V-test",
                "www.luke.com",
                "llg6yy@163.com",
                "",
                ""
        ) ;
        docket.apiInfo(apiInfo) ;
        return docket ;
    }
}
 
```



#### applicationContext-mvc

> 使用annotation方式

```
<mvc:annotation-driven />
    <context:annotation-config/>
    <context:component-scan base-package="com.luke.shop.eshop" use-default-filters="false" >
        <context:include-filter expression="org.springframework.stereotype.Controller" type="annotation"/>
    </context:component-scan>
```

> aop设置

```
<aop:aspectj-autoproxy />
    <aop:config proxy-target-class="true" />
```

> jsp 放置位置

```
<bean id="viewResolver"
          class="org.springframework.web.servlet.view.InternalResourceViewResolver">
        <property name="viewClass"
                  value="org.springframework.web.servlet.view.JstlView" />
        <property name="prefix" value="/WEB-INF/page/" />
        <property name="suffix" value=".jsp" />
    </bean>
```











### hibernate 配置

#### applicationContext-db.xml

> 使用外部文件

```
<bean id="propertyConfigurer" class="org.springframework.beans.factory.config.PropertyPlaceholderConfigurer">
              <property name="location">
                     <value>classpath:/jdbc.properties</value>
              </property>
       </bean>
```

> 数据源

```
<bean id="d1.dataSource" class="com.mchange.v2.c3p0.ComboPooledDataSource">
              <property name="driverClass" value="${d1.driver}" />
              <property name="jdbcUrl" value="${d1.url}" />
              <property name="user" value="${d1.username}" />
              <property name="password" value="${d1.password}" />
              <property name="minPoolSize" value="5" />
              <property name="maxPoolSize" value="10" />
              <property name="initialPoolSize" value="5" />
              <property name="maxIdleTime" value="60" />
       </bean>
```

> 数据库操作工具 jdbcTemplate,sessionFactory,hibernateTemplate

```
<bean id="jdbcTemplate" class="org.springframework.jdbc.core.JdbcTemplate" >
              <property name="dataSource" ref="d1.dataSource" />
       </bean>
       <bean id="sessionFactory" class="org.springframework.orm.hibernate5.LocalSessionFactoryBean">
              <property name="packagesToScan" value="com.luke.model" />
              <property name="dataSource" ref="d1.dataSource" />
              <property name="hibernateProperties">
                     <props>
                            <!--<prop key="hibernate.dialect">org.hibernate.dialect.Oracle10gDialect</prop>-->
                            <!-- org.hibernate.dialect.MySQL57InnoDBDialect-->
                            <prop key="hibernate.dialect">${d1.dialect}</prop>
                            <prop key="hibernate.connection.autocommit">false</prop>
                            <prop key="hibernate.hbm2ddl.auto">update</prop>
                            <!--<prop key="hibernate.show_sql">true</prop>-->
                            <prop key="hibernate.jdbc.batch_size">50</prop>
                            <prop key="jdbc.batch_size">50</prop>


                     </props>
              </property>
       </bean>

       <bean id="hibernateTemplet" class="org.springframework.orm.hibernate5.HibernateTemplate">
              <property name="sessionFactory" ref="sessionFactory"/>
       </bean>
       
```

> 事务

```
 <!--<tx:annotation-driven/>-->
       <aop:config proxy-target-class="true">
              <aop:pointcut expression=" execution(* com.luke.shop.eshop.*.service.impl.*.*(..))" id="serviceMethod" />
              <aop:advisor advice-ref="txAdvice" pointcut-ref="serviceMethod" />
       </aop:config>
       <tx:advice id="txAdvice" transaction-manager="transactionManager">
              <tx:attributes>
                     <tx:method name="save*" 	rollback-for="Throwable" read-only="false" />
                     <tx:method name="add*" 	rollback-for="Throwable" read-only="false" />
                     <tx:method name="delete*" 	rollback-for="Throwable" read-only="false" />
                     <tx:method name="del*" 	rollback-for="Throwable" read-only="false" />
                     <tx:method name="update*" 	rollback-for="Throwable" read-only="false" />
                     <tx:method name="edit*" 	rollback-for="Throwable" read-only="false" />
                     <tx:method name="set*" 	rollback-for="Throwable" read-only="false" />
                     <tx:method name="find*" 	rollback-for="Throwable" read-only="true" />
                     <tx:method name="load*" 	rollback-for="Throwable" read-only="true" />
                     <tx:method name="get*" 		rollback-for="Throwable" read-only="true" />
              </tx:attributes>
       </tx:advice>
```

> jdbc.properties

```
#mysql 5 localhost
d1.url=jdbc:mysql://127.0.0.1:3306/eshop?useUnicode=true&characterEncoding=utf8&autoReconnect=true&failOverReadOnly=false&useSSL=true
d1.driver=com.mysql.jdbc.Driver
d1.username=root
d1.password=root
d1.dialect=org.hibernate.dialect.MySQL57InnoDBDialect
```

```
#mysql 8 
#d1.url=jdbc:mysql://106.12.10.163:13366/eshop?useSSL=false
#d1.driver=com.mysql.cj.jdbc.Driver
#d1.username=root
#d1.password=root
#d1.dialect=org.hibernate.dialect.MySQL57InnoDBDialect
```





### log4j配置

> 默认配置

```
log4j.rootLogger=error,stdout
log4j.appender.stdout=org.apache.log4j.ConsoleAppender
log4j.appender.stdout.Target=System.out
log4j.appender.stdout.layout=org.apache.log4j.PatternLayout
log4j.appender.stdout.layout.ConversionPattern=%d{yyyy-MM-dd HH:mm:ss,SSS} %5p %c:%L - %m%n
log4j.logger.org.springframework=error
log4j.logger.org.quartz=error
log4j.logger.org.hibernate=error
log4j.logger.com.luke=debug
log4j.logger.org.hibernate.SQL=trace
log4j.logger.org.hibernate.type.BasicTypeRegistry=error
log4j.logger.org.hibernate.type.descriptor.sql.BasicBinder=debug
log4j.logger.org.hibernate.type.descriptor.sql.BasicExtractor=error
org.hibernate.type.EnumType=error
```

> 输出到文件

```
log4j.appender.luke_file=org.apache.log4j.DailyRollingFileAppender
log4j.appender.luke_file.Threshold=trace
log4j.appender.luke_file.File=${LK.Eshop}/log/log_luke_file.log
log4j.appender.luke_file.layout=org.apache.log4j.PatternLayout
log4j.appender.luke_file.layout.ConversionPattern=%d{yyyy-MM-dd HH:mm:ss,SSS} %5p %c:%L - %m%n
log4j.appender.luke_file.encoding=UTF-8
#需要org.apache.log4j.DailyRollingFileAppender每天的循环挂载器
log4j.appender.luke_file.DatePattern='.'yyyy-MM-dd
log4j.appender.R.MaxBackupIndex=1
log4j.logger.com.luke=debug,luke_file
log4j.logger.org.hibernate.SQL=trace,luke_file
log4j.logger.org.hibernate.type.BasicTypeRegistry=error,luke_file
log4j.logger.org.hibernate.type.descriptor.sql.BasicBinder=debug,luke_file
log4j.logger.org.hibernate.type.descriptor.sql.BasicExtractor=error,luke_file
org.hibernate.type.EnumType=error,luke_file
```










### java 包文件组织

```
com.luek.shop.model   //hibernate 的映射类包
com.luke.shop.tool	  //程序的工具类包
com.luke.shop.eshop   //程序业务包
```



### js 文件夹组织

```
webapp.api  spring-Swagger-api 
webapp.app	程序前台页面文件与js
webapp.file 上传文件位置
webapp.js	程序使用的js类库
webapp.plugin 页面插件文件夹  打印插件
```







