fun main() : nothing
	var x : int;
	var str : char[100];
	var str2 : char[100];
	var str3 : char[100];
{

$	x <- geti();
$	puti(abs(x));
	
	puti(ord(getc()));
	putc(chr(geti()));
	return;

	gets(100, str);
	gets(100, str2);

	strcpy(str3, str);
	puts(str);
	puts(str3);
	putc('\n');

	strcat(str, str2);
	puts(str);

	return;

	puti(strlen(str));

$	puts(str);
$$
	x <- geti();
	puti(x);

	x <- geti();
	puti(x);
$$

$$
	str[0] <- getc();
	putc(str[0]);

	str[0] <- getc();
	putc(str[0]);


	str[0] <- getc();
	putc(str[0]);

	str[1] <- getc();
	putc(str[1]);
	puts("\n");
$$


}