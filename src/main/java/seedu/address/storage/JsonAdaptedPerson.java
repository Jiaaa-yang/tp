package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Email;
import seedu.address.model.person.GithubUsername;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.team.Skill;
import seedu.address.model.team.Team;

/**
 * Jackson-friendly version of {@link Person}.
 */
class JsonAdaptedPerson {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Person's %s field is missing!";

    private final String name;
    private final String phone;
    private final String email;
    private final String username;
    private final List<JsonAdaptedTeam> teamSet = new ArrayList<>();
    private final List<JsonAdaptedSkill> skillSet = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedPerson} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedPerson(@JsonProperty("name") String name, @JsonProperty("phone") String phone,
            @JsonProperty("email") String email, @JsonProperty("username") String username,
            @JsonProperty("teamed") List<JsonAdaptedTeam> teamSet,
            @JsonProperty("skillSet") List<JsonAdaptedSkill> skillSet) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.username = username;
        if (teamSet != null) {
            this.teamSet.addAll(teamSet);
        }
        if (skillSet != null) {
            this.skillSet.addAll(skillSet);
        }
    }

    /**
     * Converts a given {@code Person} into this class for Jackson use.
     */
    public JsonAdaptedPerson(Person source) {
        name = source.getName().fullName;
        phone = source.getPhone().value;
        email = source.getEmail().value;
        username = source.getGithubUsername().value;
        teamSet.addAll(source.getTeams().stream()
                .map(JsonAdaptedTeam::new)
                .collect(Collectors.toList()));
        skillSet.addAll(source.getSkillSet().stream()
                .map(JsonAdaptedSkill::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code Person} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
     */
    public Person toModelType() throws IllegalValueException {
        final List<Team> personTeams = new ArrayList<>();
        final List<Skill> personSkillSet = new ArrayList<>();

        for (JsonAdaptedTeam team : teamSet) {
            personTeams.add(team.toModelType());
        }

        for (JsonAdaptedSkill skill : skillSet) {
            personSkillSet.add(skill.toModelType());
        }

        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(name);

        if (phone == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName()));
        }
        if (!Phone.isValidPhone(phone)) {
            throw new IllegalValueException(Phone.MESSAGE_CONSTRAINTS);
        }
        final Phone modelPhone = new Phone(phone);

        if (email == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName()));
        }
        if (!Email.isValidEmail(email)) {
            throw new IllegalValueException(Email.MESSAGE_CONSTRAINTS);
        }
        final Email modelEmail = new Email(email);

        if (username == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    GithubUsername.class.getSimpleName()));
        }
        if (!GithubUsername.isValidUsername(username)) {
            throw new IllegalValueException(GithubUsername.MESSAGE_CONSTRAINTS);
        }
        final GithubUsername modelGithubUsername = new GithubUsername(username);

        final Set<Team> modelTeams = new HashSet<>(personTeams);
        final Set<Skill> modelSkill = new HashSet<>(personSkillSet);
        return new Person(modelName, modelPhone, modelEmail, modelGithubUsername, modelTeams, modelSkill);
    }

}
