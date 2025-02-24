package com.qa.opencart.listeners;

public class GITCreation {
	//create repository in GIT
	//1. check GIT installed 
		// command: git --version
	//2. got to project
		// command: cd project path
	//3. check git folder created
		//command: dir
	//4. run command to add project to git
		//command: git remote add origin https://github.com/Sunitha-QA/MyJavaPracticeSession.git
	//5. create git ignore file for project in eclipse
		// project -> right click ->file ->.gitignore
	//6. check what is done
		// command: git status
		//will show what folders added
	//7. add project to stage/indexing - created a copy of local project in git
		//command: git add .
	
	//8. add to local repo (creates local master branch)
		//command: git commit -m "adding my java practice code"
	
	//9. moves code to remote master - our project will be available in github now
		//command: git push origin master
	
	//if any new changes/updates done to project
		//command: git status
		//shows new changes
		//command: git add "give the path of new code added" 
		//command: git commit -m "updated code"
		//command: git push origin master
	
	
	//to see commits from command prompt
			//command: git log
			//command: git log --oneline
			//HEAD ->MASTER is latest branch
		
		//how to create PR
		
		// 1. take clone from remote side
		
		//check how many branches
		// git branch
		// will show master
		
		// create new branch
		// git branch loginpage
		// git branch
		// shows two branches main is master
		
		//to switch to login page
		// git checkout loginpage
		
		//check in eclipse
		// close and open project
	
		//test
}
