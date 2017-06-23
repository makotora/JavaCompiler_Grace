.intel_syntax noprefix
.text
	.global main
L1:
quicksort_2:
	push ebp
	mov ebp, esp
	sub esp, 76
L2:
	mov eax, DWORD PTR [ebp + 16]
	mov edx, DWORD PTR [ebp + 20]
	cmp eax, edx
	jle L4
L3:
	jmp L5
L4:
	jmp quicksort_2_end
L5:
	mov eax, DWORD PTR [ebp + 20]
	mov DWORD PTR [ebp - 4], eax
L6:
	mov eax, DWORD PTR [ebp + 16]
	mov DWORD PTR [ebp - 8], eax
L7:
	mov eax, DWORD PTR [ebp - 4]
	mov edx, DWORD PTR [ebp - 8]
	cmp eax, edx
	jle L9
L8:
	jmp L41
L9:
	mov eax, DWORD PTR [ebp - 4]
	mov ecx, 4
	imul ecx
	mov ecx, DWORD PTR [ebp + 24]
	add eax, ecx
	mov DWORD PTR [ebp - 16], eax
L10:
	mov eax, DWORD PTR [ebp + 20]
	mov edx, DWORD PTR [ebp + 16]
	add eax, edx
	mov DWORD PTR [ebp - 20], eax
L11:
	mov eax, DWORD PTR [ebp - 20]
	cdq
	mov ebx, 2
	idiv ebx
	mov DWORD PTR [ebp - 24], eax
L12:
	mov eax, DWORD PTR [ebp - 24]
	mov ecx, 4
	imul ecx
	mov ecx, DWORD PTR [ebp + 24]
	add eax, ecx
	mov DWORD PTR [ebp - 28], eax
L13:
	mov edi, DWORD PTR [ebp - 16]
	mov eax, DWORD PTR [edi]
	mov edi, DWORD PTR [ebp - 28]
	mov edx, DWORD PTR [edi]
	cmp eax, edx
	jl L15
L14:
	jmp L18
L15:
	mov eax, DWORD PTR [ebp - 4]
	mov edx, 1
	add eax, edx
	mov DWORD PTR [ebp - 32], eax
L16:
	mov eax, DWORD PTR [ebp - 32]
	mov DWORD PTR [ebp - 4], eax
L17:
	jmp L9
L18:
	mov eax, DWORD PTR [ebp - 8]
	mov ecx, 4
	imul ecx
	mov ecx, DWORD PTR [ebp + 24]
	add eax, ecx
	mov DWORD PTR [ebp - 36], eax
L19:
	mov eax, DWORD PTR [ebp + 20]
	mov edx, DWORD PTR [ebp + 16]
	add eax, edx
	mov DWORD PTR [ebp - 40], eax
L20:
	mov eax, DWORD PTR [ebp - 40]
	cdq
	mov ebx, 2
	idiv ebx
	mov DWORD PTR [ebp - 44], eax
L21:
	mov eax, DWORD PTR [ebp - 44]
	mov ecx, 4
	imul ecx
	mov ecx, DWORD PTR [ebp + 24]
	add eax, ecx
	mov DWORD PTR [ebp - 48], eax
L22:
	mov edi, DWORD PTR [ebp - 36]
	mov eax, DWORD PTR [edi]
	mov edi, DWORD PTR [ebp - 48]
	mov edx, DWORD PTR [edi]
	cmp eax, edx
	jg L24
L23:
	jmp L27
L24:
	mov eax, DWORD PTR [ebp - 8]
	mov edx, 1
	sub eax, edx
	mov DWORD PTR [ebp - 52], eax
L25:
	mov eax, DWORD PTR [ebp - 52]
	mov DWORD PTR [ebp - 8], eax
L26:
	jmp L18
L27:
	mov eax, DWORD PTR [ebp - 4]
	mov edx, DWORD PTR [ebp - 8]
	cmp eax, edx
	jle L29
L28:
	jmp L7
L29:
	mov eax, DWORD PTR [ebp - 4]
	mov ecx, 4
	imul ecx
	mov ecx, DWORD PTR [ebp + 24]
	add eax, ecx
	mov DWORD PTR [ebp - 56], eax
L30:
	mov edi, DWORD PTR [ebp - 56]
	mov eax, DWORD PTR [edi]
	mov DWORD PTR [ebp - 12], eax
L31:
	mov eax, DWORD PTR [ebp - 4]
	mov ecx, 4
	imul ecx
	mov ecx, DWORD PTR [ebp + 24]
	add eax, ecx
	mov DWORD PTR [ebp - 60], eax
L32:
	mov eax, DWORD PTR [ebp - 8]
	mov ecx, 4
	imul ecx
	mov ecx, DWORD PTR [ebp + 24]
	add eax, ecx
	mov DWORD PTR [ebp - 64], eax
L33:
	mov edi, DWORD PTR [ebp - 64]
	mov eax, DWORD PTR [edi]
	mov edi, DWORD PTR [ebp - 60]
	mov DWORD PTR [edi], eax
L34:
	mov eax, DWORD PTR [ebp - 8]
	mov ecx, 4
	imul ecx
	mov ecx, DWORD PTR [ebp + 24]
	add eax, ecx
	mov DWORD PTR [ebp - 68], eax
L35:
	mov eax, DWORD PTR [ebp - 12]
	mov edi, DWORD PTR [ebp - 68]
	mov DWORD PTR [edi], eax
L36:
	mov eax, DWORD PTR [ebp - 4]
	mov edx, 1
	add eax, edx
	mov DWORD PTR [ebp - 72], eax
L37:
	mov eax, DWORD PTR [ebp - 72]
	mov DWORD PTR [ebp - 4], eax
L38:
	mov eax, DWORD PTR [ebp - 8]
	mov edx, 1
	sub eax, edx
	mov DWORD PTR [ebp - 76], eax
L39:
	mov eax, DWORD PTR [ebp - 76]
	mov DWORD PTR [ebp - 8], eax
L40:
	jmp L7
L41:
	mov esi, DWORD PTR [ebp + 24]
	push esi
L42:
	mov eax, DWORD PTR [ebp + 20]
	push eax
L43:
	mov eax, DWORD PTR [ebp - 8]
	push eax
L44:
	sub esp, 4
	push DWORD PTR [ebp + 8]
	call quicksort_2
	add esp, 20
L45:
	mov esi, DWORD PTR [ebp + 24]
	push esi
L46:
	mov eax, DWORD PTR [ebp - 4]
	push eax
L47:
	mov eax, DWORD PTR [ebp + 16]
	push eax
L48:
	sub esp, 4
	push DWORD PTR [ebp + 8]
	call quicksort_2
	add esp, 20
L49:
quicksort_2_end:
	mov esp, ebp
	pop ebp
	ret
L50:
main:
	push ebp
	mov ebp, esp
	sub esp, 0
L51:
main_end:
	mov esp, ebp
	pop ebp
	ret

.data
