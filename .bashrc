alias brc="source ~/.bashrc"

#use to find your way back home
home()
{
	cd /c;
}

#change to your dir location
alias xampp="home && cd xampp/htdocs"
alias project3="cd xampp/htdocs/project3"

#git alias's 
alias gs="git status"
alias co="git checkout -b"
alias br="git branch"
alias pull="git pull"
alias push="git push"

#use before adding files then commit
alias rmwork="rm .idea/workspace.xml"

#commits stuff
alias vc="git log --graph --pretty=format:'%Cred%h%Creset -%C(yellow)%d%Creset %s %Cgreen(%cr) %C(bold blue)<%an>%Creset' --abbrev-commit --date=relative"
alias logc="git log origin/master.."
alias showc="git show origin/master.."

#when creating a branch use alias 'co' then alias 'branchu'
#add branch name at the end of command ie git push -u origin <branch name>
alias branchu="git push -u origin"