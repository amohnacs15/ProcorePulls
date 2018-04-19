![procore_logo_big](https://user-images.githubusercontent.com/7444521/38938649-e388d94e-42da-11e8-918c-36dac645aa6e.jpg)
Our team uses GitHub for our code repository and code review. GitHub also allows you to follow your favorite open-source repositories to watch how the codebase is changing. For this exercise, we’d like you to build an Android app that shows Pull Requests (PRs) on a given repo. The main purpose of the app is to view the source changes (aka “diffs”) associated with the PRs.

Using the MVP design pattern and Retrofit.

The "Main" page simply queries the Habitica github repository and displays the title, information about when the PR was created and by whom, and the first (highest priority) label associated with it.  We query our data useing our Retrofit Client which is then mapped to our POJO using Google's GSON library. Our ArrayList of PullRequests is displayed using a RecyclerView and a pretty basic ViewHolder.

The "Diff" page was a bit more difficult because of the fact that when making a request using the *diff_url* associated with our PullRequest object we are given our response in *raw* format, not JSON like so many of us are used to using.  OkHttp3 provides us with a *ResponseBody* object which we can then use to generate a ByteStream.  Once we have the CharacterStream bridged by the InputStreamReader we can use a BufferedReader to read it line by line.  If we were going for the "unified" approach of displaying the diff we are done.

Fortunately, we get the opportunity to display the diff in the "split" format.  The simplest approach to that is that whenever a line start with a '+' or '-' we add it to it's associated list and display two ListView's side by side.  That seems like a lot of work and boilerplate code for not a very good looking UI.  I wanted to have the app display the diffs as close to the real thing (https://www.github.com) as possible.  This was done by recognizing the following pattern in the raw response:

* If a removal line is preceeding by something other than another removal line, it is a new set of change. Otherwise there is a sequence of removals that could be followed (or not) by "paired" additions.
* If an addition is preceeded by something other than a removal or additon. It is a new set of change. If it is preceeded by another addition, there is a sequence of additions that could be paired.  If it has been preceeded by any number of removals in the past then it is "paried".
* And then we just kind of display anything else

"Paired" is defined as existing code has been changed or existing code has been removed and replaced with new code on the same line(s).

Once this pattern is recognized we add the appropriate diffs to a String[] representing the left and right sides of the split or if the line is not associated with a change to the code base then it is simply displayed as a string in a row.  This results in a heterogeneous RecyclerView.  AND we displayed it in an elevated CardView because why not?

a few things are missing (I plan to circle back to these when I have time):
1. indeterminate progress
2. error states
3. using saveInstanceState and serializable (parcelable for improved efficiency) to limit network calls on orientation change

![screenshot_20180419-124916](https://user-images.githubusercontent.com/7444521/39014874-687fa858-43d0-11e8-8668-30b53ccc9981.png)
![screenshot_20180419-124949](https://user-images.githubusercontent.com/7444521/39014879-6beb4af6-43d0-11e8-9276-69862bfaaa30.png)
