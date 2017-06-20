@1
@2
	mov eax,DWORD PTR [ebp - 4]
@3
@4
@5
@6
@7
@8
	mov eax, 2
	mov DWORD PTR [ebp - 112],eax
@9
@10
@11
@12
@13
	mov edi,DWORD PTR [ebp - 128]
	mov eax DWORDPTR [edi]
	mov edx, 2
	add eax, edx
	mov DWORD PTR [ebp - 132],eax
@14
	mov eax,DWORD PTR [ebp - 132]
	mov DWORD PTR [ebp - 120],eax
@15
	mov eax, 2
	mov edx, 3
	add eax, edx
	mov DWORD PTR [ebp - 136],eax
@16
	mov eax,DWORD PTR [ebp - 136]
	mov DWORD PTR [ebp - 4],eax
@17
	mov eax,DWORD PTR [ebp - 4]
	cdq
	mov ebx, 2
	idiv ebx
	mov DWORD PTR [ebp - 140],edx
@18
	mov eax,DWORD PTR [ebp - 140]
	mov DWORD PTR [ebp - 4],eax
@19
