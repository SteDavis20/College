;
; CSU11021 Introduction to Computing I 2019/2020
; e-Test Question 3
;

	AREA	RESET, CODE, READONLY
	ENTRY

	;
	; Implement your program here
	;

	; R0 = a
	; R1 = b
	; R2 = c
	; R3 = d
	; R4 = e
	
	LDR R1, =365	; b = 365
	MOV R2, R0	; c = a
	MOV R3, #4	; d = 4
	MOV R4, #0	; e = 0
	
while1			; while 
	CMP R2, R3	; (c >= d) {
	BLO endWh1	; branch to endNow if lower
	SUB R2, R2, R3	; c = c - d
	ADD R4, R4, #1	; e = e + 1 
	B   while1	; repeat iteration	
endWh1			; }
	CMP R2, #0	; if (c = 0) {
	BNE endIf1	; branch to endIf1 if false
	LDR R1, =366	; b = 366
	MOV R2, R4	; c = e
	MOV R3, #25	; d = 25
	MOV R4, #0	; e = 0
while2			; while
	CMP R2, R3	; (c >= d) {
	BLO endWh2	; end loop if false
	SUB R2, R2, R3	; c = c - d
	ADD R4, R4, #1	; e = e + 1 
	B   while2	; repeat iteration
endWh2			; }
endIf1			; }

	CMP R2, #0	; if (c = 0) {
	BNE endIf2	; end if false
	LDR R1, =365	; b = 365
	MOV R2, R4	; c = e
	MOV R3, #4	; d = 4
	MOV R4, #0	; e = 0
while3			; while
	CMP R2, R3	; (c >= d) {
	BLO endWh3	; branch to end if false
	SUB R2, R2, R3	; c = c - d
	ADD R4, R4, #1	; e = e + 1
	B   while3	; repeat iteration
endWh3			; }
endIf2			; }

	CMP R2, #0	; if (c = 0) {
	BNE endIf3	; Branch to end if false
	LDR R1, =366	; b = 366
endIf3			; }

STOP	B	STOP

	END
