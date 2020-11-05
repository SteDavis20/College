;
; CSU11021 Introduction to Computing I 2019/2020
; GCD
;

	AREA	RESET, CODE, READONLY
	ENTRY

	; R1 = First number
	; R2 = Second number
	
	MOV R1, #27 ; a = 24 stored in R1
	MOV R2, #81 ; b = 32 stored in R2
while
	CMP R1, R2	; (a != b)
	BEQ while2	; Branch to end or "while2" if a = b
	
	CMP R1, R2	; if (a > b)
	BLS else	; if a <= b, branch to "else"
	SUB R1, R1, R2	; a = a - b
	MOV R0, R1	; store in R0
	B	while	; repeat iteration
			
else
	SUB R2, R2, R1	; b = b - a
	MOV R0, R2	; store in R2
	B	while	; repeat iteration
while2
STOP	B	STOP

	END