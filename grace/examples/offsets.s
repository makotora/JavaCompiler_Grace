@1
@2
	mov eax, 1
	mov ecx, 1
	imul ecx
	mov DWORD PTR [ebp - 30],ecx
@3
	mov eax, 0
	mov edx, DWORD PTR [ebp - 30]
	add eax, edx
	mov DWORD PTR [ebp - 34],eax
@4
	mov eax, DWORD PTR [ebp - 34]
	mov ecx, 1
	imul ecx
	getAR(m96)
	lea ecx, BYTE PTR [esi - 96]
	add eax, ecx
	mov DWORD PTR [ebp - 38],eax
@5
	mov edi, DWORD PTR [ebp - 38]
	mov eax, DWORD PTR [edi]
	getAR(m93)
	mov BYTE PTR [esi - 93], eax
@6
	mov eax, 'a'
	getAR(m93)
	mov BYTE PTR [esi - 93], eax
@7
	mov eax, DWORD PTR [ebp - 4]
@8
@9
@10
@11
	mov eax, 1
	mov ecx, 3
	imul ecx
	mov DWORD PTR [ebp - 588],ecx
@12
	mov eax, 2
	mov edx, DWORD PTR [ebp - 588]
	add eax, edx
	mov DWORD PTR [ebp - 592],eax
@13
	mov eax, DWORD PTR [ebp - 592]
	mov ecx, 4
	imul ecx
	mov DWORD PTR [ebp - 588],ecx
@14
	mov eax, 3
	mov edx, DWORD PTR [ebp - 588]
	add eax, edx
	mov DWORD PTR [ebp - 592],eax
@15
	mov eax, DWORD PTR [ebp - 592]
	mov ecx, 5
	imul ecx
	mov DWORD PTR [ebp - 588],ecx
@16
	mov eax, 4
	mov edx, DWORD PTR [ebp - 588]
	add eax, edx
	mov DWORD PTR [ebp - 592],eax
@17
	mov eax, DWORD PTR [ebp - 592]
	mov ecx, 4
	imul ecx
	lea ecx, DWORD PTR [ebp - 580]
	add eax, ecx
	mov DWORD PTR [ebp - 596],eax
@18
	mov eax, 2
	mov edi, DWORD PTR [ebp - 596]
	mov DWORD PTR [edi], eax
@19
