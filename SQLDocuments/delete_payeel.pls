create or replace PROCEDURE DELETE_PAYEE_NEW (
    p_payee_id IN MYBANK_APP_PAYEE.payee_id%TYPE,
    p_sender_account_number IN MYBANK_APP_PAYEE.sender_account_number%TYPE,
    p_payee_account_number IN MYBANK_APP_PAYEE.payee_account_number%TYPE,
    p_payee_name IN MYBANK_APP_PAYEE.payee_name%TYPE
) AS
    v_payee_id MYBANK_APP_PAYEE.payee_id%TYPE;
    v_sender_account_number MYBANK_APP_PAYEE.sender_account_number%TYPE;
    v_payee_account_number MYBANK_APP_PAYEE.payee_account_number%TYPE;
    v_payee_name MYBANK_APP_PAYEE.payee_name%TYPE;
BEGIN
    -- Check if the payee exists
    SELECT payee_id, sender_account_number, payee_account_number, payee_name
    INTO v_payee_id, v_sender_account_number, v_payee_account_number, v_payee_name
    FROM MYBANK_APP_PAYEE
    WHERE payee_id = p_payee_id;

    -- Check if the fetched payee details match the input parameters
    IF v_sender_account_number != p_sender_account_number THEN
        RAISE_APPLICATION_ERROR(-20002, 'Sender Account Number does not match');
    END IF;
    IF v_payee_account_number != p_payee_account_number THEN
        RAISE_APPLICATION_ERROR(-20003, 'Payee Account Number does not match');
    END IF;
    IF v_payee_name != p_payee_name THEN
        RAISE_APPLICATION_ERROR(-20004, 'Payee Name does not match');
    END IF;

    -- Delete the payee
    DELETE FROM MYBANK_APP_PAYEE
    WHERE payee_id = p_payee_id;
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        RAISE_APPLICATION_ERROR(-20001, 'Payee not found');
END DELETE_PAYEE_NEW;
