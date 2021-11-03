<%@ page language="java" contentType="text/html"%>
<%@ page import="java.text.*,java.util.*" %>
<html>
   <head>
      <title>jsp page</title>
      <meta charset="utf-8">
      <meta http-equiv="X-UA-Compatible" content="IE=edge">
      <meta name="viewport" content="width=device-width, initial-scale=1">
      <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
      <link rel="icon" type="image/x-icon" href="favicon.ico">
   </head>
   <body>
      <center>
         <h2>
         Wellcome <%=session.getAttribute("user_id")%>
         <h2>
      </center>
      <section id="cover" class="min-vh-100">
         <div id="cover-caption">
            <div class="container">
               <div class="row text-white">
                  <div class="col-xl-5 col-lg-6 col-md-8 col-sm-10 mx-auto text-center form p-4">
                     <h1 class="display-4 py-2 text-truncate">Add Bookmarks</h1>
                     <div class="px-2">
                        <form action="bookmarks" method="post" class="justify-content-center">
                           <table>
                              <tr>
                                 <td>
                                    <div class="form-group">
                                       <label class="sr-only">Tag</label>
                                       <input type="text" name="tag" class="form-control" placeholder="Tag">
                                    </div>
                                 </td>
                                 <td>
                                    <button type="submit" class="btn btn-primary btn-lg form-control">Get url</button>	
                                 </td>
                              </tr>
                           </table>
                           </br>
                           <button type="button" class="btn btn-success btn-lg" ><a href="addBookmarks.html" style="color: white;">Add</a></button>
                        </form>
                        <form action="bookmarks" method="get" class="justify-content-center">
                           <button type="submit" class="btn btn-success btn-lg" >Get All Bookmarks</a></button>
                           </br></br>
                           <button type="button" class="btn btn-danger btn-lg" ><a href="logout" style="color: white;">Logout</a></button>
                        </form>
                     </div>
                  </div>
               </div>
            </div>
         </div>
      </section>
   </body>
</html>