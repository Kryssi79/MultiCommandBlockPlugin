MultiCommandBlockPlugin
=======================


## Usage
Build a Command Block and use the following syntax!

<strong>Syntax</strong>
<pre>
/mcb [command1]#[interval1]; [command2]#[interval2]
</pre>

<strong>Example</strong>
<pre>
/mcb say Hello#5; say World#10
</pre>

## Shortcuts
we have also reimplement the @a @p @r shortcuts

<strong>Syntax</strong>
<b />
@a = all Player
@r = random Player
@p = next Player

<pre>
/mcb [command1] @a#[interval1]; [command2] @p#[interval2]
</pre>


<strong>Example</strong>

<pre>
/mcb tp @a -100 100 50#10; say @p is a nice Player#20
</pre>

## Screenshots

<img src="https://raw.github.com/memoryleakx/MultiCommandBlockPlugin/master/screenshots/screen01.png" border="0">
<b/>
<img src="https://raw.github.com/memoryleakx/MultiCommandBlockPlugin/master/screenshots/screen01.png" border="0">
