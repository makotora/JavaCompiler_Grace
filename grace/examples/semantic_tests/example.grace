$simple comment
$$ multiple
line 
comment
$$

fun foo() :nothing
	var k,y,x:int;
	var m:int[10];
	var p:char[3];
	fun temp():nothing;
	fun q(z:int;m:int[20];x:char):int
		var k:int;
		var pe:int[100][1];
		{	
			if z>1 then return 2;
			x <- 'a';
			q(z,m,'a');
			temp();
			foo();
			x <- "lef"[1];
			return pe[1][1];
		}
	fun temp():nothing{
			q(x,m,p[1]);
		}
{
		if(x#5 and y>5 or y>4) then{
			while(1>1) do{
				while(2>2) do{
					k <- y+m[1];
				}
				y<-q(k,m,p[1])+q(k,m,'a');
				y<-1+y;
				
			}
		}
		else{
			x<-q(1,m,'a');
		}

	y <-1;
	return ;
}