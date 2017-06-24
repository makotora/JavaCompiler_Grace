.intel_syntax noprefix
.text
	.global main
L1:
main:
	push ebp
	mov ebp, esp
	sub esp, 72
L2:
	mov eax, 0
	mov DWORD PTR [ebp - 4], eax
L3:
	mov eax, 5
	mov DWORD PTR [ebp - 20], eax
L4:
	mov eax, DWORD PTR [ebp - 20]
	mov DWORD PTR [ebp - 12], eax
L5:
	mov eax, DWORD PTR [ebp - 12]
	mov edx, 3
	add eax, edx
	mov DWORD PTR [ebp - 24], eax
L6:
	mov eax, DWORD PTR [ebp - 24]
	mov DWORD PTR [ebp - 12], eax
L7:
	mov eax, 0
	mov DWORD PTR [ebp - 28], eax
L8:
	mov eax, DWORD PTR [ebp - 28]
	mov DWORD PTR [ebp - 16], eax
L9:
	mov eax, DWORD PTR [ebp - 12]
	mov edx, 0
	sub eax, edx
	mov DWORD PTR [ebp - 32], eax
L10:
	mov eax, DWORD PTR [ebp - 32]
	mov DWORD PTR [ebp - 16], eax
L11:
	mov eax, DWORD PTR [ebp - 12]
	mov DWORD PTR [ebp - 36], eax
L12:
	mov eax, DWORD PTR [ebp - 36]
	mov DWORD PTR [ebp - 16], eax
L13:
	mov eax, DWORD PTR [ebp - 16]
	mov edx, 3
	cmp eax, edx
	jne L15
L14:
	jmp L21
L15:
	mov eax, 3
	mov DWORD PTR [ebp - 40], eax
L16:
	mov eax, DWORD PTR [ebp - 40]
	mov edx, 1
	cmp eax, edx
	jg L18
L17:
	jmp L21
L18:
	mov eax, 0
	mov DWORD PTR [ebp - 44], eax
L19:
	mov eax, DWORD PTR [ebp - 44]
	mov DWORD PTR [ebp - 16], eax
L20:
	jmp L23
L21:
	mov eax, DWORD PTR [ebp - 12]
	mov DWORD PTR [ebp - 48], eax
L22:
	mov eax, DWORD PTR [ebp - 48]
	mov DWORD PTR [ebp - 16], eax
L23:
	mov eax, DWORD PTR [ebp - 12]
	mov edx, 0
	cmp eax, edx
	jg L25
L24:
	jmp L37
L25:
	mov eax, DWORD PTR [ebp - 12]
	cdq
	mov ebx, 2
	idiv ebx
	mov DWORD PTR [ebp - 52], edx
L26:
	mov eax, DWORD PTR [ebp - 52]
	mov edx, 1
	cmp eax, edx
	je L28
L27:
	jmp L32
L28:
	mov eax, DWORD PTR [ebp - 16]
	mov edx, 2
	add eax, edx
	mov DWORD PTR [ebp - 56], eax
L29:
	mov eax, DWORD PTR [ebp - 56]
	mov edx, 5
	add eax, edx
	mov DWORD PTR [ebp - 60], eax
L30:
	mov eax, DWORD PTR [ebp - 60]
	mov DWORD PTR [ebp - 16], eax
L31:
	jmp L34
L32:
	mov eax, DWORD PTR [ebp - 16]
	mov DWORD PTR [ebp - 64], eax
L33:
	mov eax, DWORD PTR [ebp - 64]
	mov DWORD PTR [ebp - 16], eax
L34:
	mov eax, DWORD PTR [ebp - 12]
	mov edx, 1
	sub eax, edx
	mov DWORD PTR [ebp - 68], eax
L35:
	mov eax, DWORD PTR [ebp - 68]
	mov DWORD PTR [ebp - 12], eax
L36:
	jmp L23
L37:
	mov eax, DWORD PTR [ebp - 8]
	mov ecx, DWORD PTR [ebp - 4]
	imul ecx
	mov DWORD PTR [ebp - 72], eax
L38:
	mov eax, DWORD PTR [ebp - 72]
	mov DWORD PTR [ebp - 8], eax
L39:
	mov eax, DWORD PTR [ebp - 8]
	push eax
L40:
	sub esp, 4
	push 0
	call puti_1
	add esp, 12
L41:
main_1_end:
	mov esp, ebp
	pop ebp
	ret
	puts_1:
	push ebp
	mov ebp, esp
	mov eax, DWORD PTR [ebp + 16]
	push eax
	mov eax, OFFSET FLAT:printStr
	push eax
	call printf
	add esp, 8
	mov esp, ebp
	pop ebp
	ret
	puti_1:
	push ebp
	mov ebp, esp
	mov eax, DWORD PTR [ebp + 16]
	push eax
	mov eax, OFFSET FLAT:printInt
	push eax
	call printf
	add esp, 8
	mov esp, ebp
	pop ebp
	ret
	putc_1:
	push ebp
	mov ebp, esp
	mov eax, DWORD PTR [ebp + 16]
	push eax
	mov eax, OFFSET FLAT:printChar
	push eax
	call printf
	add esp, 8
	mov esp, ebp
	pop ebp
	ret
	gets_1:
	push ebp
	mov ebp, esp
	mov eax, DWORD PTR stdin
	push eax
	mov eax, DWORD PTR [ebp + 20]
	push eax
	mov eax, DWORD PTR [ebp + 16]
	push eax
	call fgets
	add esp, 12
	mov eax, 10 # Carriage return
	push eax
	mov eax, DWORD PTR [ebp + 16]
	push eax
	call strchr
	add esp, 8
	cmp eax, 0
	je grace_gets_no_newline
	mov BYTE PTR [eax], 0
	grace_gets_no_newline:
	mov esp, ebp
	pop ebp
	ret
	geti_1:
	push ebp
	mov ebp, esp
	sub esp, 4
	lea eax, DWORD PTR [ebp - 4]
	push eax
	mov eax, OFFSET FLAT:printInt
	push eax
	call scanf
	add esp, 8
	mov eax, DWORD PTR [ebp - 4]
	mov esi, DWORD PTR [ebp + 12]
	mov DWORD PTR [esi], eax
	mov esp, ebp
	pop ebp
	ret
	getc_1:
	push ebp
	mov ebp, esp
	sub esp, 1
	lea eax, DWORD PTR [ebp - 4]
	push eax
	mov eax, OFFSET FLAT:printChar
	push eax
	call scanf
	add esp, 8
	mov eax, BYTE PTR [ebp - 4]
	mov esi, DWORD PTR [ebp + 12]
	mov BYTE PTR [esi], eax
	mov esp, ebp
	pop ebp
	ret
	strlen_1:
	push ebp
	mov ebp, esp
	mov eax, DWORD PTR [ebp + 16]
	push eax
	call strlen
	add esp, 4
	mov esi, DWORD PTR [ebp + 12]
	mov DWORD PTR [esi], eax
	mov esp, ebp
	pop ebp
	ret
	strcmp_1:
	push ebp
	mov ebp, esp
	mov eax, DWORD PTR [ebp + 16]
	push eax
	mov eax, DWORD PTR [ebp + 20]
	push eax
	call strcmp
	add esp, 8
	mov esi, DWORD PTR [ebp + 12]
	mov DWORD PTR [esi], eax
	mov esp, ebp
	pop ebp
	ret
	strcpy_1:
	push ebp
	mov ebp, esp
	mov eax, DWORD PTR [ebp + 16]
	push eax
	mov eax, DWORD PTR [ebp + 20]
	push eax
	call strcpy
	add esp, 8
	mov esp, ebp
	pop ebp
	ret
	strcat_1:
	push ebp
	mov ebp, esp
	mov eax, DWORD PTR [ebp + 16]
	push eax
	mov eax, DWORD PTR [ebp + 20]
	push eax
	call strcat
	add esp, 8
	mov esp, ebp
	pop ebp
	ret

.data
	printInt: .ASCIZ "%d"
	printChar: .ASCIZ "%c"
	printStr: .ASCIZ "%s"
