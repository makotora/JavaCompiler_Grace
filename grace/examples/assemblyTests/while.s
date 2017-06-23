.intel_syntax noprefix
	.global main
	.text

L1:
	main:
	push ebp
	mov ebp, esp
	sub esp, 68
L2:
	mov eax, 0
	mov ecx, 4
	imul ecx
	lea ecx, DWORD PTR [ebp - 24]
	add eax, ecx
	mov DWORD PTR [ebp - 28], eax
L3:
	mov eax, 0
	mov edx, 1
	sub eax, edx
	mov DWORD PTR [ebp - 32], eax
L4:
	mov eax, DWORD PTR [ebp - 32]
	mov edi, DWORD PTR [ebp - 28]
	mov DWORD PTR [edi], eax
L5:
	mov eax, 1
	mov ecx, 4
	imul ecx
	lea ecx, DWORD PTR [ebp - 24]
	add eax, ecx
	mov DWORD PTR [ebp - 36], eax
L6:
	mov eax, 0
	mov edx, 1
	sub eax, edx
	mov DWORD PTR [ebp - 40], eax
L7:
	mov eax, DWORD PTR [ebp - 40]
	mov edi, DWORD PTR [ebp - 36]
	mov DWORD PTR [edi], eax
L8:
	mov eax, 2
	mov ecx, 4
	imul ecx
	lea ecx, DWORD PTR [ebp - 24]
	add eax, ecx
	mov DWORD PTR [ebp - 44], eax
L9:
	mov eax, 0
	mov edx, 1
	sub eax, edx
	mov DWORD PTR [ebp - 48], eax
L10:
	mov eax, DWORD PTR [ebp - 48]
	mov edi, DWORD PTR [ebp - 44]
	mov DWORD PTR [edi], eax
L11:
	mov eax, 3
	mov ecx, 4
	imul ecx
	lea ecx, DWORD PTR [ebp - 24]
	add eax, ecx
	mov DWORD PTR [ebp - 52], eax
L12:
	mov eax, 0
	mov edx, 1
	sub eax, edx
	mov DWORD PTR [ebp - 56], eax
L13:
	mov eax, DWORD PTR [ebp - 56]
	mov edi, DWORD PTR [ebp - 52]
	mov DWORD PTR [edi], eax
L14:
	mov eax, 4
	mov ecx, 4
	imul ecx
	lea ecx, DWORD PTR [ebp - 24]
	add eax, ecx
	mov DWORD PTR [ebp - 60], eax
L15:
	mov eax, 0
	mov edx, 1
	sub eax, edx
	mov DWORD PTR [ebp - 64], eax
L16:
	mov eax, DWORD PTR [ebp - 64]
	mov edi, DWORD PTR [ebp - 60]
	mov DWORD PTR [edi], eax
L17:
	mov eax, 0
	mov DWORD PTR [ebp - 4], eax
L18:
	mov eax, DWORD PTR [ebp - 4]
	mov ecx, 4
	imul ecx
	lea ecx, DWORD PTR [ebp - 24]
	add eax, ecx
	mov DWORD PTR [ebp - 68], eax
L19:
	mov edi, DWORD PTR [ebp - 68]
	mov eax, DWORD PTR [edi]
	mov edx, 0
	cmp eax, edx
	jl L21
L20:
	jmp L22
L21:
	jmp L18
L22:
	main_1_end:
	mov esp, ebp
	pop ebp
	ret
