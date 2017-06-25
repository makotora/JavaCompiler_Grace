.intel_syntax noprefix
.text
	.global main
L1:
main:
	push ebp
	mov ebp, esp
	sub esp, 76
	jmp L2
L2:
L3:
	jmp L6
L4:
	mov eax, 10
	mov DWORD PTR [ebp - 20], eax
L5:
	mov eax, DWORD PTR [ebp - 20]
	mov DWORD PTR [ebp - 4], eax
L6:
	mov eax, 0
	mov DWORD PTR [ebp - 4], eax
L7:
	mov eax, 5
	mov DWORD PTR [ebp - 24], eax
L8:
	mov eax, DWORD PTR [ebp - 24]
	mov DWORD PTR [ebp - 12], eax
L9:
	mov eax, DWORD PTR [ebp - 12]
	mov edx, 3
	add eax, edx
	mov DWORD PTR [ebp - 28], eax
L10:
	mov eax, DWORD PTR [ebp - 28]
	mov DWORD PTR [ebp - 12], eax
L11:
	mov eax, 0
	mov DWORD PTR [ebp - 32], eax
L12:
	mov eax, DWORD PTR [ebp - 32]
	mov DWORD PTR [ebp - 16], eax
L13:
	mov eax, DWORD PTR [ebp - 12]
	mov edx, 0
	sub eax, edx
	mov DWORD PTR [ebp - 36], eax
L14:
	mov eax, DWORD PTR [ebp - 36]
	mov DWORD PTR [ebp - 16], eax
L15:
	mov eax, DWORD PTR [ebp - 12]
	mov DWORD PTR [ebp - 40], eax
L16:
	mov eax, DWORD PTR [ebp - 40]
	mov DWORD PTR [ebp - 16], eax
L17:
	mov eax, DWORD PTR [ebp - 16]
	mov edx, 3
	cmp eax, edx
	jne L19
L18:
	jmp L27
L19:
	mov eax, 3
	mov DWORD PTR [ebp - 44], eax
L20:
	mov eax, DWORD PTR [ebp - 44]
	mov edx, 1
	cmp eax, edx
	jg L22
L21:
	jmp L27
L22:
	jmp L24
L23:
	jmp L27
L24:
	mov eax, 0
	mov DWORD PTR [ebp - 48], eax
L25:
	mov eax, DWORD PTR [ebp - 48]
	mov DWORD PTR [ebp - 16], eax
L26:
	jmp L29
L27:
	mov eax, DWORD PTR [ebp - 12]
	mov DWORD PTR [ebp - 52], eax
L28:
	mov eax, DWORD PTR [ebp - 52]
	mov DWORD PTR [ebp - 16], eax
L29:
	mov eax, DWORD PTR [ebp - 12]
	mov edx, 0
	cmp eax, edx
	jg L31
L30:
	jmp L43
L31:
	mov eax, DWORD PTR [ebp - 12]
	cdq
	mov ebx, 2
	idiv ebx
	mov DWORD PTR [ebp - 56], edx
L32:
	mov eax, DWORD PTR [ebp - 56]
	mov edx, 1
	cmp eax, edx
	je L34
L33:
	jmp L38
L34:
	mov eax, DWORD PTR [ebp - 16]
	mov edx, 2
	add eax, edx
	mov DWORD PTR [ebp - 60], eax
L35:
	mov eax, DWORD PTR [ebp - 60]
	mov edx, 5
	add eax, edx
	mov DWORD PTR [ebp - 64], eax
L36:
	mov eax, DWORD PTR [ebp - 64]
	mov DWORD PTR [ebp - 16], eax
L37:
	jmp L40
L38:
	mov eax, DWORD PTR [ebp - 16]
	mov DWORD PTR [ebp - 68], eax
L39:
	mov eax, DWORD PTR [ebp - 68]
	mov DWORD PTR [ebp - 16], eax
L40:
	mov eax, DWORD PTR [ebp - 12]
	mov edx, 1
	sub eax, edx
	mov DWORD PTR [ebp - 72], eax
L41:
	mov eax, DWORD PTR [ebp - 72]
	mov DWORD PTR [ebp - 12], eax
L42:
	jmp L29
L43:
	mov eax, DWORD PTR [ebp - 8]
	mov ecx, DWORD PTR [ebp - 4]
	imul ecx
	mov DWORD PTR [ebp - 76], eax
L44:
	mov eax, DWORD PTR [ebp - 76]
	mov DWORD PTR [ebp - 8], eax
L45:
	mov eax, DWORD PTR [ebp - 8]
	push eax
L46:
	sub esp, 4
	push 0
	call puti_1
	add esp, 12
L47:
	mov esi, OFFSET FLAT:STR1
	push esi
L48:
	sub esp, 4
	push 0
	call puts_1
	add esp, 12
L49:
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
	STR1: .ASCIZ "Done\n"
	printInt: .ASCIZ "%d"
	printChar: .ASCIZ "%c"
	printStr: .ASCIZ "%s"
