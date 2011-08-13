How to use:

0. Run the .jar file (DP_calc.jar)
1. Get OP(s)
2. Paste OP(s) (Make sure you get the part that says "Improvement Spy", "Military Spy", etc, the program parses for that)
3. Press Calc DP
4. Goto 1 as needed

NOTES:
-The newest ops from SOI, SOB, and FS will override the older one. SOM will pick the more "optimal" of the new one or old one.
 *Since the ops are parsed from beginning to end, how mean an op is defined as first, when it is pasted, second, where on the op list it is:
 *Example: PASTE OP: batch1 = [soi1, sob1, som1, sob2, soi2, fs1, som2], batch2 = [soi3] => relvant ops = [soi3, sob2, som1, fs1, som2]
-Revelation does not auto toggle shield, you will have to do it your self.
-Make sure raceList.txt is in the same folder as the jar file.  It gives choices for races if you do not provide farsight.
You could alternatively delete or add old races from the raceList.txt file. Make sure the race is case sensative and any spaces are deleted
EX: Dark Dwarf = DwarkDwarf
-May be buggy, this is the first version.

The mod DP output due to bad input >>WILL NOT<< be correct.  Examples of bad inputs includes but are  not limited to the following:
-Modification of ops (IE: changing the original nature of ops)
-Inputting multiple ops that are not of the same time (EX: SOM now and SOM 4 hours ago)
-Inputting ops from multiple kingdoms (EX: Putting FS of kingdom1 and SOM of kingdom2)
-Selecting the wrong race (EX: choosing trolls when the race is leshkins)
-Naming your own kingdom one of the parse words IE("Improvement, Military, Farsight, etc")