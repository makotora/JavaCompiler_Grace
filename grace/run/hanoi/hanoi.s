.intel_syntax noprefix
.text
	.global main
L1:

move_3:
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
	mov esi, DWORD PTR [ebp + 20]
	push esi
L5:
	sub esp, 4
	push 0
	call puts_1
	add esp, 12
L6:
	mov esi, OFFSET FLAT:STR2
	push esi
L7:
	sub esp, 4
	push 0
	call puts_1
	add esp, 12
L8:
	mov esi, DWORD PTR [ebp + 16]
	push esi
L9:
	sub esp, 4
	push 0
	call puts_1
	add esp, 12
L10:
	mov esi, OFFSET FLAT:STR3
	push esi
L11:
	sub esp, 4
	push 0
	call puts_1
	add esp, 12
L12:

move_3_end:
	mov esp, ebp
	pop ebp
	ret
L13:

hanoi_2:
	push ebp
	mov ebp, esp
	sub esp, 8
L14:
	mov eax, DWORD PTR [ebp + 28]
	mov edx, 1
	cmp eax, edx
	jge L16
L15:
	jmp L31
L16:
	mov eax, DWORD PTR [ebp + 28]
	mov edx, 1
	sub eax, edx
	mov DWORD PTR [ebp - 4], eax
L17:
	mov eax, DWORD PTR [ebp - 4]
	push eax
L18:
	mov esi, DWORD PTR [ebp + 24]
	push esi
L19:
	mov esi, DWORD PTR [ebp + 16]
	push esi
L20:
	mov esi, DWORD PTR [ebp + 20]
	push esi
L21:
	sub esp, 4
	push DWORD PTR [ebp + 8]
	call hanoi_2
	add esp, 24
L22:
	mov esi, DWORD PTR [ebp + 24]
	push esi
L23:
	mov esi, DWORD PTR [ebp + 20]
	push esi
L24:
	sub esp, 4
	push ebp
	call move_3
	add esp, 16
L25:
	mov eax, DWORD PTR [ebp + 28]
	mov edx, 1
	sub eax, edx
	mov DWORD PTR [ebp - 8], eax
L26:
	mov eax, DWORD PTR [ebp - 8]
	push eax
L27:
	mov esi, DWORD PTR [ebp + 16]
	push esi
L28:
	mov esi, DWORD PTR [ebp + 20]
	push esi
L29:
	mov esi, DWORD PTR [ebp + 24]
	push esi
L30:
	sub esp, 4
	push DWORD PTR [ebp + 8]
	call hanoi_2
	add esp, 24
L31:

hanoi_2_end:
	mov esp, ebp
	pop ebp
	ret
L32:

main:
	push ebp
	mov ebp, esp
	sub esp, 8
L33:
	mov esi, OFFSET FLAT:STR4
	push esi
L34:
	sub esp, 4
	push 0
	call puts_1
	add esp, 12
L35:
	lea esi, DWORD PTR [ebp - 8]
	push esi
L36:
	push 0
	call geti_1
	add esp, 8
L37:
	mov eax, DWORD PTR [ebp - 8]
	mov DWORD PTR [ebp - 4], eax
L38:
	mov eax, DWORD PTR [ebp - 8]
	push eax
L39:
	mov esi, OFFSET FLAT:STR5
	push esi
L40:
	mov esi, OFFSET FLAT:STR6
	push esi
L41:
	mov esi, OFFSET FLAT:STR7
	push esi
L42:
	sub esp, 4
	push ebp
	call hanoi_2
	add esp, 24
L43:

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
	STR2: .ASCIZ " to "
	STR5: .ASCIZ "left"
	STR3: .ASCIZ ".\n"
	STR1: .ASCIZ "Moving from "
	STR4: .ASCIZ "Rings: "
	STR7: .ASCIZ "middle"
	STR6: .ASCIZ "right"
	printInt: .ASCIZ "%d"
	printChar: .ASCIZ "%c"
	printStr: .ASCIZ "%s"
