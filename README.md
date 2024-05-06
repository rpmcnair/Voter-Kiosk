# CPSC 210 Personal Project

## Political Candidate Information Kiosk

### Project Description

Within this application, users will be able to look up political candidates of their local riding in Vancouver 
for the 2023 municipal election. The application will store candidates' information, platform, party affiliation, 
and other information regarding the policy reforms they are running on. Users will be voters across Vancouver 
interested in seeing and learning about the candidates they want to vote for. Users can look for specific candidates 
and their policies or look up information regarding party information and all the candidates running under certain 
parties. Furthermore, since it is a local election, candidates of the community (or their party) can add 
themselves/candidates to the kiosk and input their political information.

This project interests me since I am in the BCS program, and my first degree is in political science. 
I wanted to create a project related to political science while incorporating some of my previous knowledge with a 
coding project.

### User Stories:
- As a user, I want to be able to add a candidate to the kiosk.
- As a user, I want to be able to mark candidates as winners.
- As a user, I want to be able to look up individual candidates and there information.
- As a user, I want to be able to look up party platforms.
- As a user, I want to be able to see an overview of the elections/its details (how many candidates are running per office, parties in the election, date and time of election, polling places and other general information).

- As a user, when I restart the program I want to be able to load the election information from the previous file. 
- As a user, I want to be able to save my information, regarding candidates inputted when I quit.

### Instructions for Grader:
- You can generate the first required action related to the user story "adding multiple candidates to the candidate repository" by clicking election workers and input information and click add candidates. You can view "reading multiple candidates to the candidate repository" by clicking voter button and click view candidates.
- You can generate the second required action related to the user story "removing candidates to the candidate repository" by clicking election workers button and click remove candidates, then type in the candidates name. View the changes in the voter menu.
- You can locate my visual component by opening the main page. 
- You can save the state of my application by clicking on add candidates, adding a candidate then clicking the save candidate button.
- You can reload the state of my application by clicking on voter tab, clicking load candidates and then viewing them with the view tab.  

## Phase 4: Task 2:
**Event Log When Program is Ran:**

Mon Nov 27 23:09:30 PST 2023

Candidate Added.  

Mon Nov 27 23:09:38 PST 2023  

Candidate Added.  

Mon Nov 27 23:09:38 PST 2023  

Candidate Added.  

Mon Nov 27 23:09:38 PST 2023  

Candidate Added.  

Mon Nov 27 23:09:38 PST 2023  

Candidate Added.  

Mon Nov 27 23:09:49 PST 2023  

Candidate Removed.  

**End**


### Phase 4: Task 3:
After learning about certain design patterns as well as different ways to construct classes with implements/extends, 
here are some ways in which I would want to refactor my project. Certain classes within my project share similar characteristics
such as the Party and Candidate class, a class like 'ElectionDetails' could be introduced that could both Candidate and Party could 
inherit, encapsulating shared attributes such as name and descriptions. Which could therefore reduce code redundancy. 
Moreover, the observer pattern could be introduced to manage state changes more efficiently. Currently, the EventLog 
singleton is directly called within various parts of the system, which could be refactored, by allowing EventLog to 
observe state changes in CandidateRepository and Election classes, decoupling it. Furthermore, the code could also eventually
have more complexity with a more structured hierarchy. 




