# Lab 3
## Lauren Gager
---

## Part 1

One bug in lab 4 was in `ListExamples.java`, where the new list of words that meet StringChecker's expectations would be returned. But the bug happened where the first index of the list would constantly be replaced rather than being added to the end of the list.

Failure-inducing test:
```
  @Test
    public void testFilter(){
        StringChecker sc = new ListExamples();

        List<String> list1 = new ArrayList<String>();
        list1.add("bee");
        list1.add("bees");
        list1.add("baby");
        list1.add("dog");
        list1.add("puppy");

        List<String> expectedList1 = new ArrayList<String>();
        expectedList1.add("bees");
        expectedList1.add("baby");
        expectedList1.add("puppy");

        assertArrayEquals(expectedList1.toArray(), ListExamples.filter(list1, sc).toArray());
    }
```

Non-Failure-inducing test:
```
  @Test
    public void testFilter1(){
        StringChecker sc = new ListExamples();

        List<String> list1 = new ArrayList<String>();
        list1.add("bee");
        list1.add("bees");

        List<String> expectedList1 = new ArrayList<String>();
        expectedList1.add("bees");

        assertArrayEquals(expectedList1.toArray(), ListExamples.filter(list1, sc).toArray());
    }
```

![Image](Lab3Images/SC1.png)

Code before fixing bug:
```
import java.util.ArrayList;
import java.util.List;

interface StringChecker { boolean checkString(String s); }

class ListExamples implements StringChecker{

  public boolean checkString(String s){
    return (s.length() > 3);
  }


  // Returns a new list that has all the elements of the input list for which
  // the StringChecker returns true, and not the elements that return false, in
  // the same order they appeared in the input list;
  static List<String> filter(List<String> list, StringChecker sc) {
    List<String> result = new ArrayList<>();
    for(String s: list) {
      if(sc.checkString(s)) {
        result.add(0, s);
      }
    }
    return result;
  }


  // Takes two sorted list of strings (so "a" appears before "b" and so on),
  // and return a new list that has all the strings in both list in sorted order.
  static List<String> merge(List<String> list1, List<String> list2) {
    List<String> result = new ArrayList<>();
    int index1 = 0, index2 = 0;
    while(index1 < list1.size() && index2 < list2.size()) {
      if(list1.get(index1).compareTo(list2.get(index2)) < 0) {
        result.add(list1.get(index1));
        index1 += 1;
      }
      else {
        result.add(list2.get(index2));
        index2 += 1;
      }
    }
    while(index1 < list1.size()) {
      result.add(list1.get(index1));
      index1 += 1;
    }
    while(index2 < list2.size()) {
      result.add(list2.get(index2));
      index1 += 1;
    }
    return result;
  }


}
```

Code after fixing bug:
```
[...]

  // Returns a new list that has all the elements of the input list for which
  // the StringChecker returns true, and not the elements that return false, in
  // the same order they appeared in the input list;
  static List<String> filter(List<String> list, StringChecker sc) {
    List<String> result = new ArrayList<>();
    for(String s: list) {
      if(sc.checkString(s)) {
        result.add(s);
      }
    }
    return result;
  }


  // Takes two sorted list of strings (so "a" appears before "b" and so on),
  // and return a new list that has all the strings in both list in sorted order.
  static List<String> merge(List<String> list1, List<String> list2) {
    List<String> result = new ArrayList<>();
    int index1 = 0, index2 = 0;
    while(index1 < list1.size() && index2 < list2.size()) {
      if(list1.get(index1).compareTo(list2.get(index2)) < 0) {
        result.add(list1.get(index1));
        index1 += 1;
      }
      else {
        result.add(list2.get(index2));
        index2 += 1;
      }
    }
    while(index1 < list1.size()) {
      result.add(list1.get(index1));
      index1 += 1;
    }
    while(index2 < list2.size()) {
      result.add(list2.get(index2));
      index1 += 2;
    }
    return result;
  }


}
```

This fixes the bug because the symptoms we were seeing were that the last string that met the conditions were in the first-index. So upon observing the code, we see that we are adding to the first index rather than simply adding to the list. After changing that line of code, every word that met `StringChecker`'s condition were added to the list as intended.

---

## Part 2

`find`

`-empty` option

**Example 1: Find any files or directories that contain no text and have no files within them (none exist)**
```
laure@TABLET-R8JIQ63K MINGW32 ~/downloads/cse15l/lab4/docsearch (main)
$ find technical -empty

laure@TABLET-R8JIQ63K MINGW32 ~/downloads/cse15l/lab4/docsearch (main)
$
```

**Example 2: Find any files or directories that contain no text and have no files within them (mt.txt is empty)**
```
laure@TABLET-R8JIQ63K MINGW32 ~/downloads/cse15l/lab4/docsearch (main)
$ # upon adding a text file that is empty called 'mt.txt'

laure@TABLET-R8JIQ63K MINGW32 ~/downloads/cse15l/lab4/docsearch (main)
$ find technical -empty
technical/mt.txt
```
`empty` would be useful in cases where we made a bunch of files to code, but forgot to fill in contents for said files in order to get the program to work. While it seems somewhat niche, it could be useful to see if a file actually has contents or not. `-empty` essentially looks for an empty file and directories.

`-name` option

**Example 1: find file named chapter-1.txt in folder 911report**
```
laure@TABLET-R8JIQ63K MINGW32 ~/downloads/cse15l/lab4/docsearch (main)
$ find technical -name "chapter-1.txt"
technical/911report/chapter-1.txt
```

**Example 2: find all files in folder 911report that is a text file and has "chapter" in the beginning**
```
laure@TABLET-R8JIQ63K MINGW32 ~/downloads/cse15l/lab4/docsearch (main)
$ find ./technical/911report -name "chapter*.txt"
./technical/911report/chapter-1.txt
./technical/911report/chapter-10.txt
./technical/911report/chapter-11.txt
./technical/911report/chapter-12.txt
./technical/911report/chapter-13.1.txt
./technical/911report/chapter-13.2.txt
./technical/911report/chapter-13.3.txt
./technical/911report/chapter-13.4.txt
./technical/911report/chapter-13.5.txt
./technical/911report/chapter-2.txt
./technical/911report/chapter-3.txt
./technical/911report/chapter-5.txt
./technical/911report/chapter-6.txt
./technical/911report/chapter-7.txt
./technical/911report/chapter-8.txt
./technical/911report/chapter-9.txt
```
`-name` would be useful in order to find the path to a file you know the name of (just forgot the path), and to somewhat filter out files by their names. In the first example I provide, I am searching through `technical` for `chapter-1.txt`. In the second example, I am searching for all `chapter*.txt` files so I don't have to read the preface in `911report`. What `-name` essentially does is look for files with that name, and since I used *, it will look for anything matching it.

`-type` option

**Example 1: use find to find all the directories in folder technical**
```
laure@TABLET-R8JIQ63K MINGW32 ~/downloads/cse15l/lab4/docsearch (main)
$ find technical -type d
technical
technical/911report
technical/biomed
technical/government
technical/government/About_LSC
technical/government/Alcohol_Problems
technical/government/Env_Prot_Agen
technical/government/Gen_Account_Office
technical/government/Media
technical/government/Post_Rate_Comm
technical/plos
```

**Example 2: use find to find all files in folder 911report**
```
laure@TABLET-R8JIQ63K MINGW32 ~/downloads/cse15l/lab4/docsearch (main)
$ find technical/911report -type f
technical/911report/chapter-1.txt
technical/911report/chapter-10.txt
technical/911report/chapter-11.txt
technical/911report/chapter-12.txt
technical/911report/chapter-13.1.txt
technical/911report/chapter-13.2.txt
technical/911report/chapter-13.3.txt
technical/911report/chapter-13.4.txt
technical/911report/chapter-13.5.txt
technical/911report/chapter-2.txt
technical/911report/chapter-3.txt
technical/911report/chapter-5.txt
technical/911report/chapter-6.txt
technical/911report/chapter-7.txt
technical/911report/chapter-8.txt
technical/911report/chapter-9.txt
technical/911report/preface.txt
```
`-type` would be useful to find specific files following whatever type you are looking for. Such as how I wanted to find the directories in `technical`, or if I wanted to see all the plain files in `technical/911report`. What `-type` is doing is finding all the paths to whatever type I am looking for. So for `d`, I am looking for directories, and for `f`, I am looking for plain files.

`-ls` option

**Example 1: Use -ls to check a folder that has no subdirectories**
```
laure@TABLET-R8JIQ63K MINGW32 ~/Downloads/cse15l/lab4/docsearch (main)
$ find technical/911report -ls
3659174697628585      4 drwxr-xr-x   1 laure    197609          0 Oct 30 16:07 technical/911report
3659174697628587    120 -rw-r--r--   1 laure    197609     119387 Oct 30 16:07 technical/911report/chapter-1.txt
3940649674339244     48 -rw-r--r--   1 laure    197609      47910 Oct 30 16:07 technical/911report/chapter-10.txt
3659174697628589     72 -rw-r--r--   1 laure    197609      71968 Oct 30 16:07 technical/911report/chapter-11.txt
3659174697628590    128 -rw-r--r--   1 laure    197609     129126 Oct 30 16:07 technical/911report/chapter-12.txt
3659174697628591     92 -rw-r--r--   1 laure    197609      90943 Oct 30 16:07 technical/911report/chapter-13.1.txt
3940649674339248    112 -rw-r--r--   1 laure    197609     111804 Oct 30 16:07 technical/911report/chapter-13.2.txt
3659174697628593    152 -rw-r--r--   1 laure    197609     152185 Oct 30 16:07 technical/911report/chapter-13.3.txt
3659174697628594    264 -rw-r--r--   1 laure    197609     268853 Oct 30 16:07 technical/911report/chapter-13.4.txt
3659174697628595    288 -rw-r--r--   1 laure    197609     294230 Oct 30 16:07 technical/911report/chapter-13.5.txt
3659174697628596     80 -rw-r--r--   1 laure    197609      80751 Oct 30 16:07 technical/911report/chapter-2.txt
3659174697628597    264 -rw-r--r--   1 laure    197609     267519 Oct 30 16:07 technical/911report/chapter-3.txt
3659174697628598    100 -rw-r--r--   1 laure    197609     100212 Oct 30 16:07 technical/911report/chapter-5.txt
3659174697628599    148 -rw-r--r--   1 laure    197609     150961 Oct 30 16:07 technical/911report/chapter-6.txt
3659174697628600    128 -rw-r--r--   1 laure    197609     129949 Oct 30 16:07 technical/911report/chapter-7.txt
3659174697628601     84 -rw-r--r--   1 laure    197609      85871 Oct 30 16:07 technical/911report/chapter-8.txt
3659174697628602    148 -rw-r--r--   1 laure    197609     151529 Oct 30 16:07 technical/911report/chapter-9.txt
3659174697628603     12 -rw-r--r--   1 laure    197609       9440 Oct 30 16:07 t
```

**Example 2: Use -ls on folder that has subdirectories**
```
laure@TABLET-R8JIQ63K MINGW32 ~/Downloads/cse15l/lab4/docsearch (main)
$ find technical/government -ls
3659174697629572      0 drwxr-xr-x   1 laure    197609          0 Oct 30 16:07 technical/government
3659174697629573      8 drwxr-xr-x   1 laure    197609          0 Oct 30 16:07 technical/government/About_LSC
3659174697629575     24 -rw-r--r--   1 laure    197609      20530 Oct 30 16:07 technical/government/About_LSC/Comments_on_semiannual.txt
3659174697629587    224 -rw-r--r--   1 laure    197609     227212 Oct 30 16:07 technical/government/About_LSC/commission_report.txt
3659174697629588     16 -rw-r--r--   1 laure    197609      14195 Oct 30 16:07 technical/government/About_LSC/conference_highlights.txt
3659174697629574     16 -rw-r--r--   1 laure    197609      13889 Oct 30 16:07 technical/government/About_LSC/CONFIG_STANDARDS.txt
3940649674340245     24 -rw-r--r--   1 laure    197609      24017 Oct 30 16:07 technical/government/About_LSC/diversity_priorities.txt
3659174697629576     28 -rw-r--r--   1 laure    197609      28590 Oct 30 16:07 technical/government/About_LSC/LegalServCorp_v_VelazquezDissent.txt
3659174697629577     28 -rw-r--r--   1 laure    197609      27571 Oct 30 16:07 technical/government/About_LSC/LegalServCorp_v_VelazquezOpinion.txt
3940649674340234      8 -rw-r--r--   1 laure    197609       7222 Oct 30 16:07 technical/government/About_LSC/LegalServCorp_v_VelazquezSyllabus.txt
3659174697629579      8 -rw-r--r--   1 laure    197609       6248 Oct 30 16:07 technical/government/About_LSC/ODonnell_et_al_v_LSCdecision.txt
3659174697629580     36 -rw-r--r--   1 laure    197609      34614 Oct 30 16:07 technical/government/About_LSC/ONTARIO_LEGAL_AID_SERIES.txt
3659174697629581     48 -rw-r--r--   1 laure    197609      49122 Oct 30 16:07 technical/government/About_LSC/Progress_report.txt
3659174697629582     16 -rw-r--r--   1 laure    197609      13967 Oct 30 16:07 technical/government/About_LSC/Protocol_Regarding_Access.txt
3659174697629590     20 -rw-r--r--   1 laure    197609      18114 Oct 30 16:07 technical/government/About_LSC/reporting_system.txt
3659174697629583     44 -rw-r--r--   1 laure    197609      42167 Oct 30 16:07 technical/government/About_LSC/Special_report_to_congress.txt
3659174697629584    152 -rw-r--r--   1 laure    197609     155337 Oct 30 16:07 technical/government/About_LSC/State_Planning_Report.txt
3659174697629585     32 -rw-r--r--   1 laure    197609      30519 Oct 30 16:07 technical/government/About_LSC/State_Planning_Special_Report.txt
3659174697629586     68 -rw-r--r--   1 laure    197609      69007 Oct 30 16:07 technical/government/About_LSC/Strategic_report.txt
3659174697629591      0 drwxr-xr-x   1 laure    197609          0 Oct 30 16:07 technical/government/Alcohol_Problems
3659174697629592     32 -rw-r--r--   1 laure    197609      32413 Oct 30 16:07 technical/government/Alcohol_Problems/DraftRecom-PDF.txt
3659174697629593     36 -rw-r--r--   1 laure    197609      36564 Oct 30 16:07 technical/government/Alcohol_Problems/Session2-PDF.txt
3659174697629594     96 -rw-r--r--   1 laure    197609      95891 Oct 30 16:07 technical/government/Alcohol_Problems/Session3-PDF.txt
3659174697629595     80 -rw-r--r--   1 laure    197609      81409 Oct 30 16:07 technical/government/Alcohol_Problems/Session4-PDF.txt
3659174697629596      0 drwxr-xr-x   1 laure    197609          0 Oct 30 16:07 technical/government/Env_Prot_Agen
3659174697629597     20 -rw-r--r--   1 laure    197609      20435 Oct 30 16:07 technical/government/Env_Prot_Agen/1-3_meth_901.txt
3659174697629598     76 -rw-r--r--   1 laure    197609      74808 Oct 30 16:07 technical/government/Env_Prot_Agen/atx1-6.txt
3659174697629599    248 -rw-r--r--   1 laure    197609     250925 Oct 30 16:07 technical/government/Env_Prot_Agen/bill.txt
3659174697629600     76 -rw-r--r--   1 laure    197609      76081 Oct 30 16:07 technical/government/Env_Prot_Agen/ctf1-6.txt
3659174697629601     80 -rw-r--r--   1 laure    197609      80231 Oct 30 16:07 technical/government/Env_Prot_Agen/ctf7-10.txt
3659174697629602    136 -rw-r--r--   1 laure    197609     135728 Oct 30 16:07 technical/government/Env_Prot_Agen/ctm4-10.txt
3659174697629603     36 -rw-r--r--   1 laure    197609      33506 Oct 30 16:07 technical/government/Env_Prot_Agen/final.txt
3659174697629604     64 -rw-r--r--   1 laure    197609      63613 Oct 30 16:07 technical/government/Env_Prot_Agen/jeffordslieberm.txt
3659174697629605    196 -rw-r--r--   1 laure    197609     199381 Oct 30 16:07 technical/government/Env_Prot_Agen/multi102902.txt
3659174697629606     32 -rw-r--r--   1 laure    197609      31944 Oct 30 16:07 technical/government/Env_Prot_Agen/nov1.txt
[... continues...]
```

What `-ls` does is recursively look through a given directory for us. It acts similar to `ls -R` where it will recursively give us results. This is useful if there are many subdirectories that would be tedious to constantly search through. It isn't very useful in the first example since there are no subdirectories -- although it could be argued that it is useful because it shows that there are no subdirectories with files in them in this directory. But in the second example, it is useful since there are many different subdirectories in `government`. 

Links:
- https://www.geeksforgeeks.org/find-command-in-linux-with-examples/
- 
