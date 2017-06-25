.intel_syntax noprefix
.text
	.global main
L1:
foo_2:
	push ebp
	mov ebp, esp
	sub esp, 0
L2:
	mov eax, 5
	mov esi, DWORD PTR [ebp + 8]
	mov DWORD PTR [esi - 4], eax
L3:
foo_2_end:
	mov esp, ebp
	pop ebp
	ret
L4:
main:
	push ebp
	mov ebp, esp
	sub esp, 88
L5:
L6:
	jmp L13
L7:
L8:
L9:
L10:
L11:
L12:
L13:
	mov eax, 0
	mov DWORD PTR [ebp - 8], eax
L14:
L15:
	mov eax, 2
	mov DWORD PTR [ebp - 8], eax
L16:
	mov eax, 5
	mov DWORD PTR [ebp - 4], eax
L17:
L18:
	mov eax, 8
	mov DWORD PTR [ebp - 4], eax
L19:
L20:
	mov eax, 16
	mov DWORD PTR [ebp - 8], eax
L21:
	sub esp, 4
	push ebp
	call foo_2
	add esp, 8
L22:
L23:
	mov eax, 5
	mov DWORD PTR [ebp - 12], eax
L24:
L25:
	mov eax, 8
	mov DWORD PTR [ebp - 12], eax
L26:
L27:
	mov eax, 0
	mov DWORD PTR [ebp - 16], eax
L28:
L29:
	mov eax, 8
	mov DWORD PTR [ebp - 16], eax
L30:
L31:
	mov eax, 8
	mov DWORD PTR [ebp - 16], eax
L32:
	jmp L34
L33:
L34:
L35:
	jmp L37
L36:
L37:
	jmp L39
L38:
L39:
L40:
	mov eax, 0
	mov DWORD PTR [ebp - 16], eax
L41:
	jmp L44
L42:
L43:
L44:
	mov eax, DWORD PTR [ebp - 12]
	mov edx, 0
	cmp eax, edx
	jg L46
L45:
	jmp L58
L46:
	mov eax, DWORD PTR [ebp - 12]
	cdq
	mov ebx, 2
	idiv ebx
	mov DWORD PTR [ebp - 72], edx
L47:
	mov eax, DWORD PTR [ebp - 72]
	mov edx, 1
	cmp eax, edx
	je L49
L48:
	jmp L53
L49:
	mov eax, DWORD PTR [ebp - 16]
	mov edx, 2
	add eax, edx
	mov DWORD PTR [ebp - 76], eax
L50:
	mov eax, DWORD PTR [ebp - 76]
	mov edx, 5
	add eax, edx
	mov DWORD PTR [ebp - 80], eax
L51:
	mov eax, DWORD PTR [ebp - 80]
	mov DWORD PTR [ebp - 16], eax
L52:
	jmp L55
L53:
L54:
	mov eax, DWORD PTR [ebp - 16]
	mov DWORD PTR [ebp - 16], eax
L55:
	mov eax, DWORD PTR [ebp - 12]
	mov edx, 1
	sub eax, edx
	mov DWORD PTR [ebp - 88], eax
L56:
	mov eax, DWORD PTR [ebp - 88]
	mov DWORD PTR [ebp - 12], eax
L57:
	jmp L44
L58:
	mov eax, DWORD PTR [ebp - 8]
	push eax
L59:
	sub esp, 4
	push 0
	call puti_1
	add esp, 12
L60:
	mov esi, OFFSET FLAT:STR1
	push esi
L61:
	sub esp, 4
	push 0
	call puts_1
	add esp, 12
L62:
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
	abs_1:
	push ebp
	mov ebp, esp
	mov eax, DWORD PTR [ebp + 16]
	cdq
	xor eax, edx
	sub eax, edx
	mov esi, DWORD PTR [ebp + 12]
	mov DWORD PTR [esi], eax
	mov esp, ebp
	pop ebp
	ret
	ord_1:
	push ebp
	mov ebp, esp
	mov eax, BYTE PTR [ebp + 16]
	mov esi, DWORD PTR [ebp + 12]
	mov DWORD PTR [esi], eax
	mov esp, ebp
	pop ebp
	ret
	chr_1:
	push ebp
	mov ebp, esp
	mov eax, DWORD PTR [ebp + 16]
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
