fun main() : nothing
	var i : int;
	var size : int;
	var string : char[20];
	var array_of_strings : char[2][10];

	fun addToString(x : char[]) : nothing
		var i : int;
	{
		size <- strlen(x);
		i <- 0;

		while (i < size) do
		{
			x[i] <- chr( ord(x[i]) + 1 );

			i <- i + 1;
		}
	}

{
	puts("This program adds 1 to each char of a given string\n");
	puts("Insert a string (max 20 chars) : ");
	gets(20, string);


	puts("before: ");
	puts(string);
	putc('\n');

	addToString(string);

	puts("after: ");
	puts(string);
	putc('\n');

	puts("String\'s size is: ");
	puti(size);
	putc('\n');

	
	$Do the same passing a sub array

	puts("\nNow passing ABCD from an array of strings!\n");

	array_of_strings[1][1] <- 'A';
	array_of_strings[1][0] <- array_of_strings[1][1];


	array_of_strings[1][1] <- 'B';
	array_of_strings[1][2] <- 'C';
	array_of_strings[1][3] <- 'D';
	array_of_strings[1][4] <- '\n';
	array_of_strings[1][5] <- '\0';

	puts("before: ");
	puts(array_of_strings[1]);
	putc('\n');

	addToString(array_of_strings[1]);
	
	puts("after: ");
	puts(array_of_strings[1]);
	putc('\n');	
}