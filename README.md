<!--
  READ_ME STRUCTURE
  1. Introduction
    a. What is it?
    b. (Optional) Why did you make it? 
  2. Dependencies
  3. Try it out yourself (mentione the .jar file located on root)
-->

# Java-Calculator
<p> This is a calculator that is fully coded in java where the frontend uses the Swing framework.
It also uses a dependencies that allow calculation based on a string expression. This calculator supports most of the features of a basic scientific calculator. At the moment
it can not calculate sin, cos, tan. The reason I created this project was to enhance my experience of using Java, the Swing framework, and learning how to add dependencies to projects
using Maven.</p>
<img src="https://github.com/gnikkoch96/Java-Calculator/blob/master/images-for-github/Screenshot_1.png"/>


<h2> Dependencies </h2>
<ul> 
<li> StringUtils.isNumeric(String) - is used in the project to help determine if the user had entered a valid integer </li>
<li><a href="https://mathparser.org/"> Math Parser </a> - used to parse through the string expression and calculate said expresion 
   <ul>
      <li> This dependency really made my project. If it weren't for this parser, I would have had to create a class like an ArithmeticManager that activates a method based on  
        a button press </li>
      <li> One thing that I could say about this parser is that the website seems outdated and kind of messy which made it troubling to look for the things I wanted </li> 
  </ul>
  </li>
</ul>

<h2> Try it out yourself </h2>
<p> In the root directory there is a jar file that you can execute. </p>



