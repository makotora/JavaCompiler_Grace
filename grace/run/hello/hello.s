.intel_syntax noprefix
.text
	.global main
L1:

main:
	push ebp
	mov ebp, esp
	sub esp, 0
L2:
	mov esi, OFFSET FLAT:STR1
	push esi
L3:
	sub esp, 4
	push 0
	call puts_1
	add esp, 12
L4:

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
	STR1: .ASCIZ "Hello world!\n"
	printInt: .ASCIZ "%d"
	printChar: .ASCIZ "%c"
	printStr: .ASCIZ "%s"
