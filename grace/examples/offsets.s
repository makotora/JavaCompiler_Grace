L1:
	foo2:
	push ebp
	mov ebp esp
	sub esp, 38
L2:
	mov eax, 1
	mov ecx, 1
	imul ecx
	mov DWORD PTR [ebp - 30],ecx
L3:
	mov eax, 0
	mov edx, DWORD PTR [ebp - 30]
	add eax, edx
	mov DWORD PTR [ebp - 34],eax
L4:
	mov eax, DWORD PTR [ebp - 34]
	getAR(m96)
	lea ecx, BYTE PTR [esi - 96]
	add eax, ecx
	mov DWORD PTR [ebp - 38],eax
L5:
	mov edi, DWORD PTR [ebp - 38]
	mov eax, DWORD PTR [edi]
	getAR(m93)
	mov BYTE PTR [esi - 93], eax
L6:
	mov eax, 'a'
	getAR(m93)
	mov BYTE PTR [esi - 93], eax
L7:
	mov eax, DWORD PTR [ebp - 4]
L8:
L9:
	mov esp ebp
	pop ebp
	ret
L10:
	foo12:
	push ebp
	mov ebp esp
	sub esp, 0
L11:
	mov eax, DWORD PTR [ebp + 16]
L12:
L13:
	mov esp ebp
	pop ebp
	ret
L14:
	main1:
	push ebp
	mov ebp esp
	sub esp, 588
L15:
	mov eax, DWORD PTR [ebp - 4]
	push eax
L16:
	lea esi, DWORD PTR [ebp - 588]
	push esi
L17:
L18:
	mov eax, DWORD PTR [ebp - 588]
	mov DWORD PTR [ebp - 4],eax
L19:
	mov esp ebp
	pop ebp
	ret
