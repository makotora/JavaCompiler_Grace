@1
@2
	mov eax,DWORD PTR [ebp - 4]
@3
@4
@5
@6
@7
	mov eax, 2
	mov DWORD PTR [ebp - 108],eax
@8
	mov eax, 2
	mov edx, 3
	add eax, edx
	mov DWORD PTR [ebp - 112],eax
@9
	mov eax,DWORD PTR [ebp - 112]
	mov DWORD PTR [ebp - 4],eax
@10
	mov eax,DWORD PTR [ebp - 4]
	cdq
	mov ebx, 2
	idiv ebx
	mov DWORD PTR [ebp - 116],edx
@11
	mov eax,DWORD PTR [ebp - 116]
	mov DWORD PTR [ebp - 4],eax
@12
