.intel_syntax noprefix
	.global main
	.text

L1:
	swap_3:
	push ebp
	mov ebp, esp
	sub esp, 4
L2:
	mov esi, DWORD PTR [ebp + 20]
	mov eax, DWORD PTR [esi]
	mov DWORD PTR [ebp - 4], eax
L3:
	mov esi, DWORD PTR [ebp + 16]
	mov eax, DWORD PTR [esi]
	mov esi, DWORD PTR [ebp + 20]
	mov DWORD PTR [esi], eax
L4:
	mov eax, DWORD PTR [ebp - 4]
	mov esi, DWORD PTR [ebp + 16]
	mov DWORD PTR [esi], eax
L5:
	swap_3_end:
	mov esp, ebp
	pop ebp
	ret
L6:
	bsort_2:
	push ebp
	mov ebp, esp
	sub esp, 40
L7:
	mov eax, 1
	mov DWORD PTR [ebp - 4], eax
L8:
	mov eax, DWORD PTR [ebp - 4]
	mov edx, 0
	cmp eax, edx
	jg L10
L9:
	jmp L31
L10:
	mov eax, 0
	mov DWORD PTR [ebp - 4], eax
L11:
	mov eax, 0
	mov DWORD PTR [ebp - 8], eax
L12:
	mov eax, DWORD PTR [ebp + 20]
	mov edx, 1
	sub eax, edx
	mov DWORD PTR [ebp - 12], eax
L13:
	mov eax, DWORD PTR [ebp - 8]
	mov edx, DWORD PTR [ebp - 12]
	cmp eax, edx
	jl L15
L14:
	jmp L30
L15:
	mov eax, DWORD PTR [ebp - 8]
	mov ecx, 4
	imul ecx
	mov ecx, DWORD PTR [ebp + 16]
	add eax, ecx
	mov DWORD PTR [ebp - 16], eax
L16:
	mov eax, DWORD PTR [ebp - 8]
	mov edx, 1
	add eax, edx
	mov DWORD PTR [ebp - 20], eax
L17:
	mov eax, DWORD PTR [ebp - 20]
	mov ecx, 4
	imul ecx
	mov ecx, DWORD PTR [ebp + 16]
	add eax, ecx
	mov DWORD PTR [ebp - 24], eax
L18:
	mov edi, DWORD PTR [ebp - 16]
	mov eax, DWORD PTR [edi]
	mov edi, DWORD PTR [ebp - 24]
	mov edx, DWORD PTR [edi]
	cmp eax, edx
	jg L20
L19:
	jmp L27
L20:
	mov eax, DWORD PTR [ebp - 8]
	mov ecx, 4
	imul ecx
	mov ecx, DWORD PTR [ebp + 16]
	add eax, ecx
	mov DWORD PTR [ebp - 28], eax
L21:
	lea esi, DWORD PTR [ebp - 28]
	push esi
L22:
	mov eax, DWORD PTR [ebp - 8]
	mov edx, 1
	add eax, edx
	mov DWORD PTR [ebp - 32], eax
L23:
	mov eax, DWORD PTR [ebp - 32]
	mov ecx, 4
	imul ecx
	mov ecx, DWORD PTR [ebp + 16]
	add eax, ecx
	mov DWORD PTR [ebp - 36], eax
L24:
	lea esi, DWORD PTR [ebp - 36]
	push esi
L25:
	sub esp, 4
	push ebp
	call swap_3
	add esp, 16
L26:
	mov eax, 1
	mov DWORD PTR [ebp - 4], eax
L27:
	mov eax, DWORD PTR [ebp - 8]
	mov edx, 1
	add eax, edx
	mov DWORD PTR [ebp - 40], eax
L28:
	mov eax, DWORD PTR [ebp - 40]
	mov DWORD PTR [ebp - 8], eax
L29:
	jmp L12
L30:
	jmp L8
L31:
	bsort_2_end:
	mov esp, ebp
	pop ebp
	ret
L32:
	putArray_2:
	push ebp
	mov ebp, esp
	sub esp, 8
L33:
	mov eax, 0
	mov DWORD PTR [ebp - 4], eax
L34:
	mov eax, DWORD PTR [ebp - 4]
	mov edx, DWORD PTR [ebp + 20]
	cmp eax, edx
	jl L36
L35:
	jmp L41
L36:
	mov eax, DWORD PTR [ebp - 4]
	mov edx, 0
	cmp eax, edx
	je L38
L37:
	jmp L38
L38:
	mov eax, DWORD PTR [ebp - 4]
	mov edx, 1
	add eax, edx
	mov DWORD PTR [ebp - 8], eax
L39:
	mov eax, DWORD PTR [ebp - 8]
	mov DWORD PTR [ebp - 4], eax
L40:
	jmp L34
L41:
	putArray_2_end:
	mov esp, ebp
	pop ebp
	ret
L42:
	main:
	push ebp
	mov ebp, esp
	sub esp, 96
L43:
	mov eax, 65
	mov DWORD PTR [ebp - 4], eax
L44:
	mov eax, 0
	mov DWORD PTR [ebp - 8], eax
L45:
	mov eax, DWORD PTR [ebp - 8]
	mov edx, 16
	cmp eax, edx
	jl L47
L46:
	jmp L57
L47:
	mov eax, DWORD PTR [ebp - 4]
	mov ecx, 137
	imul ecx
	mov DWORD PTR [ebp - 76], ecx
L48:
	mov eax, DWORD PTR [ebp - 76]
	mov edx, 220
	add eax, edx
	mov DWORD PTR [ebp - 80], eax
L49:
	mov eax, DWORD PTR [ebp - 80]
	mov edx, DWORD PTR [ebp - 8]
	add eax, edx
	mov DWORD PTR [ebp - 84], eax
L50:
	mov eax, DWORD PTR [ebp - 84]
	cdq
	mov ebx, 101
	idiv ebx
	mov DWORD PTR [ebp - 88], edx
L51:
	mov eax, DWORD PTR [ebp - 88]
	mov DWORD PTR [ebp - 4], eax
L52:
	mov eax, DWORD PTR [ebp - 8]
	mov ecx, 4
	imul ecx
	lea ecx, DWORD PTR [ebp - 72]
	add eax, ecx
	mov DWORD PTR [ebp - 92], eax
L53:
	mov eax, DWORD PTR [ebp - 4]
	mov edi, DWORD PTR [ebp - 92]
	mov DWORD PTR [edi], eax
L54:
	mov eax, DWORD PTR [ebp - 8]
	mov edx, 1
	add eax, edx
	mov DWORD PTR [ebp - 96], eax
L55:
	mov eax, DWORD PTR [ebp - 96]
	mov DWORD PTR [ebp - 8], eax
L56:
	jmp L45
L57:
	push esi
L58:
	mov eax, 16
	push eax
L59:
	lea esi, DWORD PTR [ebp - 72]
	push esi
L60:
	sub esp, 4
	push ebp
	call putArray_2
	add esp, 20
L61:
	mov eax, 16
	push eax
L62:
	lea esi, DWORD PTR [ebp - 72]
	push esi
L63:
	sub esp, 4
	push ebp
	call bsort_2
	add esp, 16
L64:
	push esi
L65:
	mov eax, 16
	push eax
L66:
	lea esi, DWORD PTR [ebp - 72]
	push esi
L67:
	sub esp, 4
	push ebp
	call putArray_2
	add esp, 20
L68:
	main_1_end:
	mov esp, ebp
	pop ebp
	ret
