# Lab 1
## Lauren Gager
---
**For each of the commands `cd`, `ls`, and `cat`, and using the workspace you created in this lab:**
1. Share an example of using the command with no arguments.
2. Share an exmaple of using the command with a path to a directory as an argument.
3. Share an example of using the command with a path to a file as an argument.

**So that’s 9 total examples (3 for each command). For each, include:**

- A screenshot or Markdown code block showing the command and its output
- What the working directory was when the command was run
- A sentence or two explaining why you got that output (e.g. what was in the filesystem, what it meant to have no arguments).
- Indicate whether the output is an error or not, and if it’s an error, explain why it’s an error.

**cd**
```
# Working Directory: home
#
[user@sahara ~]$ cd
[user@sahara ~]$
```
`cd` helps us navigate through our directories. So when we don't give it a directory name to go to, nothing will really happen. This is not an error.

```
# Working Directory: home
#
[user@sahara ~]$ cd lecture1
[user@sahara ~/lecture1]$
```
`cd` changed directory to lecture1. This is not an error. I used a relative shorthand, meaning that lecture1 had to be a assessible from home.

```
`# Working Directory: home
#
[user@sahara ~]$ cd /home/lecture1/Hello.java
bash: cd: /home/lecture1/Hello.java: Not a directory
[user@sahara ~]$
```
`cd` tried to find the directory, however it concluded that Hello.java is not a directory; rather it is a file. cd stands for (to the best of my understanding) change directory, so trying to change into Hello.java, which is a file, will result in that response.
