fun main() : nothing
	var i : int;
	var x : int[5];
	var chr : char;
	var string : char[10];

	fun boo(ref x: int; byval : int; y : int[5]; byval2 : int) : nothing
	{
		;
	}

	fun foo(x : char[]; ref y : int) : nothing
	{
		puts("here");
		x[0] <- 'D';
		puts("there");
	}
{

	string[0] <- 'S';
	string[1] <- 'I';
	string[2] <- 'C';
	string[3] <- 'K';
	string[4] <- '\t';
	string[5] <- '.';
	string[6] <- '\n';
	string[7] <- '\0';

	$puts(string);
	foo(string, i);
	puts(string);
	
	return;

	$puts("before\n");
	$foo("SUCK", i);
	puts("after\n");
	
	
	boo(x[0], x[0], x, i);
	x[0] <- -1;
	x[1] <- -1;
	x[2] <- -1;
	x[3] <- -1;
	x[4] <- 1;
	chr <- "A BIG STRIIIIING\t\n"[2];
	i <- 0;
	while x[i] < 0 do
	{
		i <- i + 1;
	}
}