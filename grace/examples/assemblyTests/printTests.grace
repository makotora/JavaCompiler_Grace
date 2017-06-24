fun main() : nothing
	var i : int;
	var j : int;
	var ret : int;
	var x : int[2][10];
	var chr : char;
	var string : char[10];

	fun foo(x : int[][10]) : int

		fun boo() : nothing
			var array : int[2][10];
		{
			puts("In boo function with i: ");
			puti(i);
			putc('\n');

			while (i < 20) do
			{

				puti(x[i div 10][i mod 10]);
				putc(' ');

				x[i div 10][i mod 10] <- x[i div 10][i mod 10] + 1;

				puti(x[i div 10][i mod 10]);
				putc('\n');putc('\n');

				i <- i + 1;
			}

			j <- 100;
			ret <- foo(x);
			puts("Here\n");
			$puts("Foo returned : ");
			$puti(j);
			$putc('\n');
		}
	{
		puts("In foo function with i: ");
		puti(i);
		putc('\n');
		puts("In foo function with j: ");
		puti(j);
		putc('\n');

		while (i > 0) do
		{
			i <- i - 1;
			puti(i div 10);
			putc(' ');
			puti(i mod 10);
			putc(' ');

			puti(x[i div 10][i mod 10]);
			putc('\n');
			putc('\n');

			chr <- 'z';

		}

		putc('\n');

		
		if (j # 100) then
		{
			puts("Calling boo with i: ");
			puti(i);
			putc('\n');
			boo();
		}

		return i;
	}

{
	$test math
	puts("1 + 24 = ");
	puti(1+24);putc('\n');

	puts("51 - 100 = ");
	puti(51 - 100);putc('\n');

	puts("5 * 12 = ");
	puti(5 * 12);putc('\n');

	puts("10 div 3 = ");
	puti(10 div 3);putc('\n');

	puts("5 * 6 = ");
	puti(5 * 6);putc('\n');putc('\n');putc('\n');



	i <- 0;
	while (i < 20) do
	{
			puti(i div 10);
			putc(' ');
			puti(i mod 10);
			putc(' ');
			x[i div 10][i mod 10] <- i;
			puti(x[i div 10][i mod 10]);
			putc('\n');putc('\n');

			i <- i + 1;
	}

	chr <- 'a';
	putc(chr);
	putc('\n');

	puts("Calling foo with i: ");
	puti(i);
	putc('\n');


	j <- 0;
	ret <- foo(x);
	puts("Foo returned : ");
	puti(ret);
	putc('\n');

	puts("i is : ");
	puti(i);
	putc('\n');

	putc(chr);
	putc('\n');

	putc('\n');
}