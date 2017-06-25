fun main() : nothing
var x : int;
var y : int;
var w : int;
var z : int;
{
	$this condition can be erased (the jump that follows stays)
	$this is deadcode
	$it can be completely erased (advanced)

	if 0 > 0 then
		x <- 6 + 4; $this can become 10

	x <- 0; $this could disappear

	w <- 2 + 3; $should become 5
	w <- w + 3; $should become 8
	
	z <- w * 0; $should become 0
	
	z <- w - 0;  $should become w
	z <- w + 0; $should become w

	$ following 1+2 can become 3
	$ and maybe the 3 > 1 can become a simple jump (advanced)
	$ 4 > 1 can turn into a simple jump

	if ( z # 3 and (1+2) > 1 and 4 > 1) then 
		z <- w - w; $should become 0
	else
		z <- w * 1;  $should become w

	while ( w > 0 ) do
	{
		if (w mod 2 = 1) then
			z <- z + 2 + 5; $should become z + 7
		else
			z <- z + 0; $should become z or simply disappear (advanced)

		w <- w - 1;
	}

	y <- y * x; $should become y*0 and maybe after another pass..0!
	puti(y); $if y wasnt used here, it could disappear
	puts("Done\n");
}