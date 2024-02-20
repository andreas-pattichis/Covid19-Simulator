# COVID SIMULATION

A simulation program that models the process of transmitting a virus to a set of individuals through personal contact or contact with infected areas.

<p align="center">
  <img src="https://github-production-user-asset-6210df.s3.amazonaws.com/63289392/306253973-52efc263-3e8b-44f8-aa8f-5600e9895e11.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=AKIAVCODYLSA53PQK4ZA%2F20240220%2Fus-east-1%2Fs3%2Faws4_request&X-Amz-Date=20240220T120544Z&X-Amz-Expires=300&X-Amz-Signature=4aef79a3027fba73e433234bbce070ec10b98bb90373c6da3432ff20dbfd1a10&X-Amz-SignedHeaders=host&actor_id=63289392&key_id=0&repo_id=260473376" width="600"/>
</p>

---

## 📰 Desciption

The COVID Simulation project is a Java-based program that simulates the spread of a virus among individuals within different areas. It provides a visual representation of how the virus can be transmitted through personal contact and contact with infected areas. Users can customize the simulation by setting probabilities for virus transmission and specifying the number of people, infected individuals, immune individuals, and area dimensions. The simulation runs for a defined duration, and at the end, it displays the final count of healthy, infected, and immune individuals in each area. The goal of the project is to help understand the dynamics of virus spread and the effectiveness of preventive measures.


---

# Manual for the simulation

In this manual, we will explain briefly what the user should input in every instruction.

******************************************************************************************
1. When the simulation runs the following message is printed on conole to inform the user 
   about the simulation:

"                  SIMULATION OF THE PROCESS                  
         OF TRANSMITING A VIRUS TO A SET OF INDIVIDUALS        
---------------------------------------------------------------
       The virus is spread from person to person through       
         personal contact with an infected person or by        
            contact with an infected area of the site.       

A person can be: 1) healthy  (wearing a GREEN shirt)          
                 2) infected (wearing a RED shirt)              
                 3) immune   (wearing a BLUE shirt)          
                 && wearing a mask                         

When floor is colored: 1) ORANGE, it just got infected         
                       2) YELLOW, it will stop being infected

The borders are indicated with a circle.                       
The borders have 3 different colors depending on the area they 
lead to:               1) PINK                                 
                       2) WHITE                                
                       3) LIGHT BLUE                           

Remember that borders can only be placed on the perimeter of the
areas.
*If there are more than 3 areas, different borders can have the 
same colors.                                                 

*Every person stays at the place for 1 minute.                 
*For each minute, the simulation shows the movement in each area.
---------------------------------------------------------------"

******************************************************************************************
2. After this, the simulation warns the user that now it is the time to input the information
   with this message:

"Now you get to decide the specifications of the simulation: "

******************************************************************************************
3. First of all it asks the user, if he/she would like to enter his/her own probabilities
   or continue with the default that we have already set up:

Here's an example:
"Would you like to set your own propabilities for the affection 
 of the Virus to the people?

 Press 0 for NO or 1 for YES: 1

 Give the percantages about:

 (1) Person-to-Person probability of infection without mask:(%) 60

 (2) Person-to-Person probability of infection with mask:(%) 30

 (3) Place-to-Person probability of infection without mask:(%) 50

 (4) Place-to-Person probability of infection with mask:(%) 25 "

If the user enters 0: the program shows the user the default probabilities
If the user enters 1: the user inserts their own probabilities (they have to be between 0
                      and 100)

******************************************************************************************
4. Then the user is asked to enter the number of areas:
 
Here's an example:
"The number of areas that people will interact: 2 "

If the user enters a number bigger than 1, then he/she will have the option to add borders.
Also, if the user enters a number bigger than 1, instruction 5. will repeated for all the areas

******************************************************************************************
5. Then the user is asked to enter the number of people, infected people, immune people, 
   height of the area, width of the area and information about borders(if areas are more than 1):

Here's an example:
"The number of people for area A: 10

 The number of infected people for area A: 2

 The number of immune people for area A: 2

 The height for area A: 10

 The width for area A: 10

 Do you want area  to have borders?
 Press 0 for NO or 1 for YES:1

 With how many areas do you want area A to border with? 1

 No.1 area you want area A to be bordered with (only Characters):B

 How many borders do you want for the area B? 2

 Give the borders in points(e.g 2,0): 

 1: 0,0
 2: 0,1 "

As you can see in the example above the user chose to place borders that lead to area B.

******************************************************************************************
6. After the user has entered the information for all the areas, he/she also enters the time
   of the program in minutes and the duration that the places will be affected.

Here's an example:
"Give the time of the program (in minutes): 10

 Give the duration that each place will be affected: 2 "

******************************************************************************************
7. After this, all the initial information for each area are printed on the console.

Here's an example:
"AT THE BEGINNING THERE ARE: 

 6 people that are healthy and not immune.

 2 people that are infected.

 2 people that are immune.

 ---------------------------------------------------------------"

This message is printed for the amount of areas that exist in the simulation. 

******************************************************************************************
8. After this, every times it changes to another area it prints the information for the people there.
Here's an example:
"Now in area A: 
 10 people are in this area.

 The coordinates of all the people in this area: 
 1: 6,1
 2: 2,2
 3: 1,6
 4: 4,5
 5: 8,2
 6: 8,9
 7: 6,5
 8: 9,4
 9: 1,8
 10: 5,6

 ---------------------------------------------------------------"

Also the user can the areas as they are drawn in the Standard Draw window

******************************************************************************************
9. Finally, all the final information for each area are printed on the console.

Here's an example:
"FINALLY THERE ARE:

 1 person that is healthy and not immune.

 7 people that are infected.

 2 people that are immune.

 As you can see, with these results is important to STAY HOME!!

---------------------------------------------------------------"

This message is printed for the amount of areas that exist in the simulation. 

---

## 🛠 Initialization & Setup

<pre class="notranslate"><code>git clone https://github.com/andreas-pattichis/Covid19-Simulator.git
</code></pre>


---

## 🚀 Building and Running
Run the Project in IntelliJ IDEA
