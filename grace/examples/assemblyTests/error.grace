fun main() : nothing
	var i : int;
	var x : int[5];
	var chr : char;
	var string : char[10];

	fun foo(x : char[]; ref y : int) : nothing
	{
		puts("here");
		x[0] <- 'D';
		puts(x);
	}
{
$	puts("here");

	string[0] <- 'S';
	string[1] <- 'I';
	string[2] <- 'C';
	string[3] <- 'K';
	string[4] <- '\t';
	string[5] <- '.';
	string[6] <- '\n';
	string[7] <- '\0';


	$puts("before\n");
	foo(string, i);
	puts(string);
	
}