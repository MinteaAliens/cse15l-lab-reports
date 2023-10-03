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

**ls**
```
# Working Directory: home
#
[user@sahara ~]$ ls
lecture1
```
`ls` shows what we can currently access in our directory. Meaning that we can simply type `cd lecture1` rather than typing the full path `cd /home/lecture1`. Anything the terminal prints out with `ls` shows what is in our directory.

```
# Working Directory: home
#
[user@sahara ~]$ls lecture1
Hello.class Hello.java messages README
[user@sahara ~]$ ls messages
ls: cannot access 'messages': No such file or directory
[user@sahara ~]$ ls /home/lecture1/messages
en-us.txt es-mx.txt zh-cn.txt
```
`ls` shows us what we can currently access in the directory we provide. *However*, the current directory must be able to access the directory being requested in order to provide what is inside that directory. `/home/` does not have access to `messages` directly, so by providing the full path, it can see what is in the directory.

```
# Working Directory: home
#
[user@sahara ~]$ ls /home/lecture1/Hello.java
/home/lecture1/Hello.java
```
`ls` provides information as specified when given a file name rather than a directory. I believe in the future we will learn more about the different commands that can be paired up with `ls` in order to view more infromation on the file we provide. In general, `ls` is a good way to help navigate and gather information about whatever we provide for it.

**cat**
```
# Working Directory: home
#
[user@sahara ~]$ cat

```
Since we did not provide any file, `cat` ended up "crashing" the terminal. The terminal stopped responding correctly and did not allow me to type any input. This is an error. `cat` typically outputs the contents of the file, but when given nothing...

```
# Working Directory: home
#
[user@sahara ~]$ cat lecture1
cat: lecture1: Is a directory
```
`cat` lets us know that lecture1 is a directory rather than a file it can read the contents of. I believe this is not an error, this is simply `cat` letting us know that we gave it a directory that does not have readable content. Perhaps we would use `ls` instead in order to see the files or subordinates of the directory.

```
# Working Directory: home
#
[user@sahara ~]$ cat /home/lecture1/Hello.java
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class Hello {
  public static void main(String[] args) throws IOException {
    String content = Files.readString(Path.of(args[0]), StandardCharsets.UTF_8);    
    System.out.println(content);
  }
}
```
`cat` outputs the content of (a) given file(s). We gave it a single file: `Hello.java`, and it printed out every single line inside the file. 
