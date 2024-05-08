CREATE OR REPLACE PROCEDURE DELETE_PAYEE (
    p_payee_id IN MYBANK_APP_PAYEE.payee_id%TYPE,
    p_sender_account_number IN MYBANK_APP_PAYEE.sender_account_number%TYPE,
    p_payee_account_number IN MYBANK_APP_PAYEE.payee_account_number%TYPE,
    p_payee_name IN MYBANK_APP_PAYEE.payee_name%TYPE
) AS
    v_count NUMBER;
BEGIN
    -- Check if the payee exists
    SELECT COUNT(*)
    INTO v_count
    FROM MYBANK_APP_PAYEE
    WHERE payee_id = p_payee_id
    AND sender_account_number = p_sender_account_number
    AND payee_account_number = p_payee_account_number
    AND payee_name = p_payee_name;

    IF v_count = 0 THEN
        -- Payee not found, raise an exception
        RAISE_APPLICATION_ERROR(-20002, 'Payee not found');
    ELSE
        -- Delete the payee
        DELETE FROM MYBANK_APP_PAYEE
        WHERE payee_id = p_payee_id
        AND sender_account_number = p_sender_account_number
        AND payee_account_number = p_payee_account_number
        AND payee_name = p_payee_name;
    END IF;
END DELETE_PAYEE;
/

execute DELETE_PAYEE(104,225792454013,145792454013,'Avinash');