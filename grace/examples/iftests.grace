fun p():nothing
var x : int;
var y : int[4];
{
	if x > 4 then
	{
		while y[x] < 10 or (x < 2 and x # 2) do
		{
			if (x = 5) then
				x <- 6;
			else
				x <- x;
		}
	}
}