fun main() : nothing
	var i : int;
	var x : int[5];
	var chr : char;
	var string : char[10];
	var array_of_strings : char[2][10];

	fun foo(x : char[]; ref y : int) : nothing
	{
		$puts("here");
		x[0] <- 'D';
		$puts(x);
	}

{
$	puts("here");
	
	string[1] <- 'S';
	string[0] <- string[1];

	string[1] <- 'I';
	string[2] <- 'C';
	string[3] <- 'K';
	string[4] <- '\t';
	string[5] <- '.';
	string[6] <- '\n';
	string[7] <- '\0';


	puts("before\n");
	puts(string);
	foo(string, i);
	puts("after\n");
	puts(string);

	array_of_strings[1][1] <- 'S';
	array_of_strings[1][0] <- array_of_strings[1][1];

	puts("Now trying passing a sub array to foo\n");

	array_of_strings[1][1] <- 'I';
	array_of_strings[1][2] <- 'C';
	array_of_strings[1][3] <- 'K';
	array_of_strings[1][4] <- '\n';
	array_of_strings[1][5] <- '\0';

	puts("before\n");
	puts(array_of_strings[1]);
	foo(array_of_strings[1], i);
	puts("after\n");
	puts(array_of_strings[1]);
	
}