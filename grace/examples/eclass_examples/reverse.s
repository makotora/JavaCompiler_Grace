.intel_syntax noprefix
.text
	.global main
L1:
reverse_2:
	push ebp
	mov ebp, esp
	sub esp, 36
L2:
	mov esi, DWORD PTR [ebp + 16]
	push esi
L3:
	lea esi, DWORD PTR [ebp - 12]
	push esi
L4:
	push 0
	call strlen_1
	add esp, 12
L5:
	mov eax, DWORD PTR [ebp - 12]
	mov DWORD PTR [ebp - 8], eax
L6:
	mov eax, 0
	mov DWORD PTR [ebp - 4], eax
L7:
	mov eax, DWORD PTR [ebp - 4]
	mov edx, DWORD PTR [ebp - 8]
	cmp eax, edx
	jl L9
L8:
	jmp L17
L9:
	mov eax, DWORD PTR [ebp - 4]
	mov esi, DWORD PTR [ebp + 8]
	lea ecx, BYTE PTR [esi - 32]
	add eax, ecx
	mov DWORD PTR [ebp - 16], eax
L10:
	mov eax, DWORD PTR [ebp - 8]
	mov edx, DWORD PTR [ebp - 4]
	sub eax, edx
	mov DWORD PTR [ebp - 20], eax
L11:
	mov eax, DWORD PTR [ebp - 20]
	mov edx, 1
	sub eax, edx
	mov DWORD PTR [ebp - 24], eax
L12:
	mov eax, DWORD PTR [ebp - 24]
	mov ecx, DWORD PTR [ebp + 16]
	add eax, ecx
	mov DWORD PTR [ebp - 28], eax
L13:
	mov edi, DWORD PTR [ebp - 28]
	mov eax, BYTE PTR [edi]
	mov edi, DWORD PTR [ebp - 16]
	mov BYTE PTR [edi], eax
L14:
	mov eax, DWORD PTR [ebp - 4]
	mov edx, 1
	add eax, edx
	mov DWORD PTR [ebp - 32], eax
L15:
	mov eax, DWORD PTR [ebp - 32]
	mov DWORD PTR [ebp - 4], eax
L16:
	jmp L7
L17:
	mov eax, DWORD PTR [ebp - 4]
	mov esi, DWORD PTR [ebp + 8]
	lea ecx, BYTE PTR [esi - 32]
	add eax, ecx
	mov DWORD PTR [ebp - 36], eax
L18:
	mov eax, 0
	mov edi, DWORD PTR [ebp - 36]
	mov BYTE PTR [edi], eax
L19:
reverse_2_end:
	mov esp, ebp
	pop ebp
	ret
L20:
main:
	push ebp
	mov ebp, esp
	sub esp, 32
L21:
	mov esi, OFFSET FLAT:STR1
	push esi
L22:
	sub esp, 4
	push ebp
	call reverse_2
	add esp, 12
L23:
	lea esi, BYTE PTR [ebp - 32]
	push esi
L24:
	sub esp, 4
	push 0
	call puts_1
	add esp, 12
L25:
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
	STR1: .ASCIZ "\n!dlrow olleH"
	printInt: .ASCIZ "%d"
	printChar: .ASCIZ "%c"
	printStr: .ASCIZ "%s"
